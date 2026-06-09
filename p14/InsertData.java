package p14;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertData {
    public static void main(String[] args) {
        String sql = "INSERT INTO mahasiswa (nim, nama, jurusan) VALUES (?, ?, ?)";
        
        // Data 10 mahasiswa
        String[][] dataMahasiswa = {
            {"M001", "Andi Pratama", "Informatika"},
            {"M002", "Budi Santoso", "Sistem Informasi"},
            {"M003", "Citra Lestari", "Teknik Komputer"},
            {"M004", "Dewi Anggraini", "Informatika"},
            {"M005", "Eko Wijaya", "Teknik Komputer"},
            {"M006", "Fajar Nugroho", "Sistem Informasi"},
            {"M007", "Gita Permata", "Informatika"},
            {"M008", "Hendra Saputra", "Teknik Komputer"},
            {"M009", "Intan Kusuma", "Sistem Informasi"},
            {"M010", "Joko Prasetyo", "Informatika"}
        };
        
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            // Loop insert 10 data menggunakan Batch
            for (String[] mhs : dataMahasiswa) {
                pstmt.setString(1, mhs[0]); // nim
                pstmt.setString(2, mhs[1]); // nama
                pstmt.setString(3, mhs[2]); // jurusan
                pstmt.addBatch();
            }
            
            pstmt.executeBatch();
            System.out.println("10 data mahasiswa berhasil dimasukkan!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
