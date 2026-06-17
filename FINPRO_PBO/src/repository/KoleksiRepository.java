package repository;

import database.Koneksi;
import model.ItemKoleksi;
import model.Manga;
import model.ActionFigure;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KoleksiRepository {
    public List<ItemKoleksi> getAllKoleksi() {
        List<ItemKoleksi> list = new ArrayList<>();
        // Fix: explicit column list instead of SELECT * to prevent schema-drift bugs
        String sql = "SELECT id_koleksi, judul, kategori, harga_sewa, stok, atribut_khusus FROM koleksi";
        try (Connection conn = Koneksi.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String kategori = rs.getString("kategori");
                if ("Manga".equalsIgnoreCase(kategori)) {
                    list.add(new Manga(
                            rs.getString("id_koleksi"),
                            rs.getString("judul"),
                            rs.getDouble("harga_sewa"),
                            rs.getInt("stok"),
                            rs.getString("atribut_khusus")));
                } else {
                    list.add(new ActionFigure(
                            rs.getString("id_koleksi"),
                            rs.getString("judul"),
                            rs.getDouble("harga_sewa"),
                            rs.getInt("stok"),
                            rs.getString("atribut_khusus")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(ItemKoleksi item) {
        String sql = "INSERT INTO koleksi (id_koleksi, judul, kategori, harga_sewa, stok, atribut_khusus) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getIdKoleksi());
            pstmt.setString(2, item.getJudul());
            pstmt.setString(3, item.getKategori());
            pstmt.setDouble(4, item.getHargaSewa());
            pstmt.setInt(5, item.getStok());
            pstmt.setString(6, item.getAtributKhusus());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(ItemKoleksi item) {
        String sql = "UPDATE koleksi SET judul=?, kategori=?, harga_sewa=?, stok=?, atribut_khusus=? WHERE id_koleksi=?";
        try (Connection conn = Koneksi.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getJudul());
            pstmt.setString(2, item.getKategori());
            pstmt.setDouble(3, item.getHargaSewa());
            pstmt.setInt(4, item.getStok());
            pstmt.setString(5, item.getAtributKhusus());
            pstmt.setString(6, item.getIdKoleksi());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM koleksi WHERE id_koleksi=?";
        try (Connection conn = Koneksi.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ItemKoleksi getById(String id) {
        String sql = "SELECT id_koleksi, judul, kategori, harga_sewa, stok, atribut_khusus FROM koleksi WHERE id_koleksi = ?";
        // Fix: ResultSet now inside try-with-resources to prevent resource leak
        try (Connection conn = Koneksi.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String kategori = rs.getString("kategori");
                    if ("Manga".equalsIgnoreCase(kategori)) {
                        return new Manga(
                                rs.getString("id_koleksi"),
                                rs.getString("judul"),
                                rs.getDouble("harga_sewa"),
                                rs.getInt("stok"),
                                rs.getString("atribut_khusus"));
                    } else {
                        return new ActionFigure(
                                rs.getString("id_koleksi"),
                                rs.getString("judul"),
                                rs.getDouble("harga_sewa"),
                                rs.getInt("stok"),
                                rs.getString("atribut_khusus"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
