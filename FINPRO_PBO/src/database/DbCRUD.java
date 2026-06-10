package database;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DbCRUD {

    // ==========================================
    // 1. USER CRUD OPERATIONS
    // ==========================================

    public static boolean verifyLogin(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Gagal memverifikasi login: " + e.getMessage());
        }
        return false;
    }

    public static boolean addUser(String username, String password, String role) {
        String sql = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan user: " + e.getMessage());
        }
        return false;
    }

    public static void listAllUsers() {
        String sql = "SELECT id_user, username, role FROM user";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n=== DATA USER ===");
            System.out.printf("%-5s | %-15s | %-10s\n", "ID", "Username", "Role");
            System.out.println("----------------------------------------");
            while (rs.next()) {
                System.out.printf("%-5d | %-15s | %-10s\n", 
                    rs.getInt("id_user"),
                    rs.getString("username"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            System.err.println("Gagal menampilkan list user: " + e.getMessage());
        }
    }

    // ==========================================
    // 2. KOLEKSI CRUD OPERATIONS
    // ==========================================

    public static boolean insertKoleksi(String id, String judul, String kategori, double hargaSewa, int stok, String atributKhusus) {
        String sql = "INSERT INTO koleksi (id_koleksi, judul, kategori, harga_sewa, stok, atribut_khusus) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, id);
            pstmt.setString(2, judul);
            pstmt.setString(3, kategori);
            pstmt.setDouble(4, hargaSewa);
            pstmt.setInt(5, stok);
            pstmt.setString(6, atributKhusus);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal menyimpan koleksi: " + e.getMessage());
        }
        return false;
    }

    public static boolean updateKoleksi(String id, String judul, double hargaSewa, int stok, String atributKhusus) {
        String sql = "UPDATE koleksi SET judul = ?, harga_sewa = ?, stok = ?, atribut_khusus = ? WHERE id_koleksi = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, judul);
            pstmt.setDouble(2, hargaSewa);
            pstmt.setInt(3, stok);
            pstmt.setString(4, atributKhusus);
            pstmt.setString(5, id);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal mengupdate koleksi: " + e.getMessage());
        }
        return false;
    }

    public static boolean deleteKoleksi(String id) {
        String sql = "DELETE FROM koleksi WHERE id_koleksi = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal menghapus koleksi: " + e.getMessage());
        }
        return false;
    }

    public static void listAllKoleksi() {
        String sql = "SELECT * FROM koleksi";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n=== INVENTARIS KOLEKSI ===");
            System.out.printf("%-8s | %-25s | %-15s | %-12s | %-5s | %-20s\n", 
                "ID", "Judul", "Kategori", "Harga Sewa", "Stok", "Atribut Khusus");
            System.out.println("--------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-8s | %-25s | %-15s | %-12.2f | %-5d | %-20s\n", 
                    rs.getString("id_koleksi"),
                    rs.getString("judul"),
                    rs.getString("kategori"),
                    rs.getDouble("harga_sewa"),
                    rs.getInt("stok"),
                    rs.getString("atribut_khusus")
                );
            }
        } catch (SQLException e) {
            System.err.println("Gagal menampilkan inventaris koleksi: " + e.getMessage());
        }
    }

    // ==========================================
    // 3. RENTAL (TRANSACTIONAL) CRUD OPERATIONS
    // ==========================================

    public static boolean pinjamKoleksi(String namaPenyewa, String idKoleksi, LocalDate tanggalPinjam) {
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement decStockStmt = null;
        PreparedStatement insertRentalStmt = null;
        
        try {
            conn = Koneksi.getConnection();
            conn.setAutoCommit(false); // Begin Transaction
            
            // 1. Check stock of collectible
            String checkSql = "SELECT stok FROM koleksi WHERE id_koleksi = ?";
            checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, idKoleksi);
            
            int currentStock = 0;
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    currentStock = rs.getInt("stok");
                } else {
                    System.out.println("Koleksi dengan ID " + idKoleksi + " tidak ditemukan!");
                    conn.rollback();
                    return false;
                }
            }
            
            if (currentStock <= 0) {
                System.out.println("Stok Koleksi " + idKoleksi + " kosong! Transaksi sewa ditolak.");
                conn.rollback();
                return false;
            }
            
            // 2. Decrement stock
            String decStockSql = "UPDATE koleksi SET stok = stok - 1 WHERE id_koleksi = ?";
            decStockStmt = conn.prepareStatement(decStockSql);
            decStockStmt.setString(1, idKoleksi);
            decStockStmt.executeUpdate();
            
            // 3. Insert transaction record
            String insertRentalSql = "INSERT INTO rental (nama_penyewa, id_koleksi, tanggal_pinjam, status) VALUES (?, ?, ?, 'Dipinjam')";
            insertRentalStmt = conn.prepareStatement(insertRentalSql);
            insertRentalStmt.setString(1, namaPenyewa);
            insertRentalStmt.setString(2, idKoleksi);
            insertRentalStmt.setDate(3, Date.valueOf(tanggalPinjam));
            insertRentalStmt.executeUpdate();
            
            conn.commit(); // Commit Transaction
            System.out.println("Peminjaman oleh " + namaPenyewa + " untuk barang " + idKoleksi + " berhasil dicatat.");
            return true;
            
        } catch (SQLException e) {
            System.err.println("Gagal memproses transaksi pinjam: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback on error
                    System.out.println("Transaksi dibatalkan (Rollback).");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // Safe clean-up of resources
            try {
                if (checkStmt != null) checkStmt.close();
                if (decStockStmt != null) decStockStmt.close();
                if (insertRentalStmt != null) insertRentalStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean kembalikanKoleksi(int idRental, LocalDate tanggalKembali) {
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement incStockStmt = null;
        PreparedStatement updateRentalStmt = null;
        
        try {
            conn = Koneksi.getConnection();
            conn.setAutoCommit(false); // Begin Transaction
            
            // 1. Get rental record and item price
            String checkSql = "SELECT r.id_koleksi, r.tanggal_pinjam, r.status, k.harga_sewa "
                    + "FROM rental r JOIN koleksi k ON r.id_koleksi = k.id_koleksi "
                    + "WHERE r.id_rental = ?";
            checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, idRental);
            
            String idKoleksi = null;
            LocalDate tanggalPinjam = null;
            String status = null;
            double hargaSewa = 0;
            
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    idKoleksi = rs.getString("id_koleksi");
                    tanggalPinjam = rs.getDate("tanggal_pinjam").toLocalDate();
                    status = rs.getString("status");
                    hargaSewa = rs.getDouble("harga_sewa");
                } else {
                    System.out.println("Transaksi rental dengan ID " + idRental + " tidak ditemukan!");
                    conn.rollback();
                    return false;
                }
            }
            
            if ("Dikembalikan".equals(status)) {
                System.out.println("Transaksi rental dengan ID " + idRental + " sudah berstatus dikembalikan.");
                conn.rollback();
                return false;
            }
            
            // 2. Calculate rental fee based on duration
            long days = ChronoUnit.DAYS.between(tanggalPinjam, tanggalKembali);
            if (days <= 0) {
                days = 1; // Minimum charge is 1 day
            }
            double totalBiaya = days * hargaSewa;
            
            // 3. Increment stock
            String incStockSql = "UPDATE koleksi SET stok = stok + 1 WHERE id_koleksi = ?";
            incStockStmt = conn.prepareStatement(incStockSql);
            incStockStmt.setString(1, idKoleksi);
            incStockStmt.executeUpdate();
            
            // 4. Update rental record
            String updateRentalSql = "UPDATE rental SET tanggal_kembali = ?, total_biaya = ?, status = 'Dikembalikan' WHERE id_rental = ?";
            updateRentalStmt = conn.prepareStatement(updateRentalSql);
            updateRentalStmt.setDate(1, Date.valueOf(tanggalKembali));
            updateRentalStmt.setDouble(2, totalBiaya);
            updateRentalStmt.setInt(3, idRental);
            updateRentalStmt.executeUpdate();
            
            conn.commit(); // Commit Transaction
            System.out.println("Pengembalian rental ID " + idRental + " berhasil. Durasi: " + days + " hari. Total Biaya: Rp " + totalBiaya);
            return true;
            
        } catch (SQLException e) {
            System.err.println("Gagal memproses transaksi kembali: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaksi dibatalkan (Rollback).");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (checkStmt != null) checkStmt.close();
                if (incStockStmt != null) incStockStmt.close();
                if (updateRentalStmt != null) updateRentalStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void listAllRental() {
        String sql = "SELECT r.id_rental, r.nama_penyewa, r.id_koleksi, k.judul, r.tanggal_pinjam, r.tanggal_kembali, r.total_biaya, r.status "
                + "FROM rental r JOIN koleksi k ON r.id_koleksi = k.id_koleksi";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n=== DAFTAR TRANSAKSI RENTAL ===");
            System.out.printf("%-5s | %-12s | %-8s | %-20s | %-12s | %-12s | %-12s | %-10s\n", 
                "ID", "Penyewa", "Item ID", "Judul Koleksi", "Tgl Pinjam", "Tgl Kembali", "Total Biaya", "Status");
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                Date tglKembali = rs.getDate("tanggal_kembali");
                String tglKembaliStr = (tglKembali == null) ? "-" : tglKembali.toString();
                
                System.out.printf("%-5d | %-12s | %-8s | %-20s | %-12s | %-12s | %-12.2f | %-10s\n", 
                    rs.getInt("id_rental"),
                    rs.getString("nama_penyewa"),
                    rs.getString("id_koleksi"),
                    rs.getString("judul"),
                    rs.getDate("tanggal_pinjam").toString(),
                    tglKembaliStr,
                    rs.getDouble("total_biaya"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.err.println("Gagal menampilkan daftar rental: " + e.getMessage());
        }
    }
}
