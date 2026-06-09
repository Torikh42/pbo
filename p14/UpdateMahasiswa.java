package p14;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateMahasiswa {
    public static void main(String[] args) {
        String sql = "UPDATE mahasiswa SET nama = ? WHERE nim = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, "Citra Larasati"); // Nama baru
            pstmt.setString(2, "M003");           // NIM yang diubah
            
            int affected = pstmt.executeUpdate();
            System.out.println("Data diupdate: " + affected + " baris");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
