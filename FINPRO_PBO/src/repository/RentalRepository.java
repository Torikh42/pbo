package repository;

import database.Koneksi;
import model.Rental;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class RentalRepository {
    
    public List<Rental> getAllRental() {
        List<Rental> list = new ArrayList<>();
        String sql = "SELECT * FROM rental";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(new Rental(
                    rs.getInt("id_rental"),
                    rs.getString("nama_penyewa"),
                    rs.getString("id_koleksi"),
                    rs.getDate("tanggal_pinjam"),
                    rs.getDate("tanggal_kembali"),
                    rs.getDouble("total_biaya"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean sewa(String namaPenyewa, String idKoleksi, Date tanggalPinjam) {
        Connection conn = null;
        try {
            conn = Koneksi.getConnection();
            conn.setAutoCommit(false);

            // Fix: PreparedStatement inside try-with-resources to prevent resource leak
            // Cek stok
            String sqlCek = "SELECT stok FROM koleksi WHERE id_koleksi = ?";
            try (PreparedStatement pstmtCek = conn.prepareStatement(sqlCek)) {
                pstmtCek.setString(1, idKoleksi);
                try (ResultSet rs = pstmtCek.executeQuery()) {
                    if (rs.next()) {
                        int stok = rs.getInt("stok");
                        if (stok <= 0) {
                            conn.rollback();
                            return false; // Stok habis
                        }
                    } else {
                        conn.rollback();
                        return false; // Barang tidak ditemukan
                    }
                }
            }

            // Kurangi stok
            String sqlUpdateStok = "UPDATE koleksi SET stok = stok - 1 WHERE id_koleksi = ?";
            try (PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdateStok)) {
                pstmtUpdate.setString(1, idKoleksi);
                pstmtUpdate.executeUpdate();
            }

            // Insert rental
            String sqlInsert = "INSERT INTO rental (nama_penyewa, id_koleksi, tanggal_pinjam, status) VALUES (?, ?, ?, 'Dipinjam')";
            try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {
                pstmtInsert.setString(1, namaPenyewa);
                pstmtInsert.setString(2, idKoleksi);
                pstmtInsert.setDate(3, tanggalPinjam);
                pstmtInsert.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    public boolean kembalikan(int idRental, Date tanggalKembali) {
        Connection conn = null;
        try {
            conn = Koneksi.getConnection();
            conn.setAutoCommit(false);

            // Fix: Ambil data rental DAN validasi status = 'Dipinjam' sekaligus
            // Ini mencegah bug stok bertambah berkali-kali jika barang sudah dikembalikan
            String sqlData = "SELECT r.id_koleksi, r.tanggal_pinjam, k.harga_sewa FROM rental r " +
                             "JOIN koleksi k ON r.id_koleksi = k.id_koleksi " +
                             "WHERE r.id_rental = ? AND r.status = 'Dipinjam'";
            try (PreparedStatement pstmtData = conn.prepareStatement(sqlData)) {
                pstmtData.setInt(1, idRental);
                try (ResultSet rs = pstmtData.executeQuery()) {
                    if (rs.next()) {
                        String idKoleksi = rs.getString("id_koleksi");
                        Date tanggalPinjam = rs.getDate("tanggal_pinjam");
                        double hargaSewa = rs.getDouble("harga_sewa");

                        // Hitung biaya
                        long days = ChronoUnit.DAYS.between(tanggalPinjam.toLocalDate(), tanggalKembali.toLocalDate());
                        if (days <= 0) days = 1; // Minimal 1 hari
                        double totalBiaya = days * hargaSewa;

                        // Update rental
                        String sqlUpdateRental = "UPDATE rental SET tanggal_kembali = ?, total_biaya = ?, status = 'Dikembalikan' WHERE id_rental = ?";
                        try (PreparedStatement pstmtUpdateRental = conn.prepareStatement(sqlUpdateRental)) {
                            pstmtUpdateRental.setDate(1, tanggalKembali);
                            pstmtUpdateRental.setDouble(2, totalBiaya);
                            pstmtUpdateRental.setInt(3, idRental);
                            pstmtUpdateRental.executeUpdate();
                        }

                        // Tambah stok
                        String sqlUpdateStok = "UPDATE koleksi SET stok = stok + 1 WHERE id_koleksi = ?";
                        try (PreparedStatement pstmtUpdateStok = conn.prepareStatement(sqlUpdateStok)) {
                            pstmtUpdateStok.setString(1, idKoleksi);
                            pstmtUpdateStok.executeUpdate();
                        }

                        conn.commit();
                        return true;
                    } else {
                        // Tidak ditemukan atau statusnya sudah 'Dikembalikan'
                        conn.rollback();
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}
