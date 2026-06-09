package p14;

import java.sql.Connection;
import java.sql.Statement;

public class Mahasiswa {
    public static void main(String[] args) {
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement()) {
             
            // Menggunakan Text Block (""") agar query SQL mudah dibaca
            String sql = """
                CREATE TABLE IF NOT EXISTS mahasiswa (
                    nim VARCHAR(10) PRIMARY KEY,
                    nama VARCHAR(50),
                    jurusan VARCHAR(50)
                )
            """;
            
            stmt.execute(sql);
            System.out.println("Tabel mahasiswa berhasil dibuat di MySQL Docker!");
            
        } catch (Exception e) {
            System.out.println("Gagal membuat tabel: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
