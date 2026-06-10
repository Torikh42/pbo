package database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class SetupDatabase {
    public static void main(String[] args) {
        System.out.println("Memulai setup database...");
        
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Drop tables if they exist (order is important due to foreign keys)
            System.out.println("Membersihkan tabel lama jika ada...");
            stmt.executeUpdate("DROP TABLE IF EXISTS rental");
            stmt.executeUpdate("DROP TABLE IF EXISTS koleksi");
            stmt.executeUpdate("DROP TABLE IF EXISTS user");
            
            // Create user table
            System.out.println("Membuat tabel 'user'...");
            String createUserTable = "CREATE TABLE user ("
                    + "id_user INT AUTO_INCREMENT PRIMARY KEY, "
                    + "username VARCHAR(50) UNIQUE NOT NULL, "
                    + "password VARCHAR(255) NOT NULL, "
                    + "role VARCHAR(20) NOT NULL"
                    + ")";
            stmt.executeUpdate(createUserTable);
            
            // Create koleksi table
            System.out.println("Membuat tabel 'koleksi'...");
            String createKoleksiTable = "CREATE TABLE koleksi ("
                    + "id_koleksi VARCHAR(10) PRIMARY KEY, "
                    + "judul VARCHAR(150) NOT NULL, "
                    + "kategori ENUM('Manga', 'Action Figure') NOT NULL, "
                    + "harga_sewa DOUBLE NOT NULL, "
                    + "stok INT NOT NULL, "
                    + "atribut_khusus VARCHAR(100)"
                    + ")";
            stmt.executeUpdate(createKoleksiTable);
            
            // Create rental table
            System.out.println("Membuat tabel 'rental'...");
            String createRentalTable = "CREATE TABLE rental ("
                    + "id_rental INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nama_penyewa VARCHAR(100) NOT NULL, "
                    + "id_koleksi VARCHAR(10) NOT NULL, "
                    + "tanggal_pinjam DATE NOT NULL, "
                    + "tanggal_kembali DATE, "
                    + "total_biaya DOUBLE DEFAULT 0.0, "
                    + "status ENUM('Dipinjam', 'Dikembalikan') DEFAULT 'Dipinjam', "
                    + "FOREIGN KEY (id_koleksi) REFERENCES koleksi(id_koleksi) ON DELETE CASCADE"
                    + ")";
            stmt.executeUpdate(createRentalTable);
            
            // Seed User dummy data
            System.out.println("Memasukkan data dummy ke tabel 'user'...");
            stmt.executeUpdate("INSERT INTO user (username, password, role) VALUES ('admin', 'admin123', 'Admin')");
            stmt.executeUpdate("INSERT INTO user (username, password, role) VALUES ('staff', 'staff123', 'Staff')");
            
            // Seed Koleksi dummy data
            System.out.println("Memasukkan data dummy ke tabel 'koleksi'...");
            stmt.executeUpdate("INSERT INTO koleksi (id_koleksi, judul, kategori, harga_sewa, stok, atribut_khusus) "
                    + "VALUES ('MGA001', 'One Piece Vol 100', 'Manga', 5000.0, 5, 'Vol 100')");
            stmt.executeUpdate("INSERT INTO koleksi (id_koleksi, judul, kategori, harga_sewa, stok, atribut_khusus) "
                    + "VALUES ('MGA002', 'Naruto Vol 72', 'Manga', 4000.0, 3, 'Vol 72')");
            stmt.executeUpdate("INSERT INTO koleksi (id_koleksi, judul, kategori, harga_sewa, stok, atribut_khusus) "
                    + "VALUES ('FIG001', 'Nendoroid Luffy Gear 5', 'Action Figure', 15000.0, 2, 'Good Smile Company')");
            stmt.executeUpdate("INSERT INTO koleksi (id_koleksi, judul, kategori, harga_sewa, stok, atribut_khusus) "
                    + "VALUES ('FIG002', 'POP Luffy Gear 5', 'Action Figure', 50000.0, 1, 'MegaHouse')");
            
            // Seed Rental dummy data
            System.out.println("Memasukkan data dummy ke tabel 'rental'...");
            stmt.executeUpdate("INSERT INTO rental (nama_penyewa, id_koleksi, tanggal_pinjam, tanggal_kembali, total_biaya, status) "
                    + "VALUES ('Budi', 'MGA001', '2026-06-01', '2026-06-05', 20000.0, 'Dikembalikan')");
            stmt.executeUpdate("INSERT INTO rental (nama_penyewa, id_koleksi, tanggal_pinjam, status) "
                    + "VALUES ('Andi', 'FIG001', '2026-06-08', 'Dipinjam')");
            
            System.out.println("Setup database BERHASIL diselesaikan!");
            
        } catch (SQLException e) {
            System.err.println("Setup database GAGAL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
