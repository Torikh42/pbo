package lkp14;

import java.sql.Connection;
import java.sql.Statement;

public class SetupDatabase {
    public static void main(String[] args) {
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement()) {
             
            // 1. Buat Tabel Mata Kuliah
            String sqlMatkul = """
                CREATE TABLE IF NOT EXISTS matakuliah (
                    kode_mk VARCHAR(10) PRIMARY KEY,
                    nama_mk VARCHAR(100),
                    sks INT
                )
            """;
            stmt.execute(sqlMatkul);
            
            // 2. Buat Tabel KRS (Tabel Relasi)
            String sqlKrs = """
                CREATE TABLE IF NOT EXISTS krs (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nim VARCHAR(10),
                    kode_mk VARCHAR(10),
                    FOREIGN KEY (nim) REFERENCES mahasiswa(nim) ON DELETE CASCADE,
                    FOREIGN KEY (kode_mk) REFERENCES matakuliah(kode_mk) ON DELETE CASCADE
                )
            """;
            stmt.execute(sqlKrs);
            
            // 3. Masukkan Dummy Data Mata Kuliah
            // Kita gunakan IGNORE agar jika file dijalankan berulang kali, tidak terjadi error duplikasi
            String sqlInsertMatkul = """
                INSERT IGNORE INTO matakuliah (kode_mk, nama_mk, sks) VALUES 
                ('MK001', 'Algoritma dan Pemrograman', 3),
                ('MK002', 'Basis Data', 3),
                ('MK003', 'Struktur Data', 3),
                ('MK004', 'Pemrograman Berorientasi Objek', 3),
                ('MK005', 'Jaringan Komputer', 3)
            """;
            stmt.execute(sqlInsertMatkul);
            
            System.out.println("Setup Database untuk Sistem KRS berhasil!");
            System.out.println("Tabel 'matakuliah' dan 'krs' sudah siap digunakan beserta data mata kuliah.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
