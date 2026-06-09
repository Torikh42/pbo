package p14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CariMahasiswaByJurusan {
    public static void main(String[] args) {
        String sql = "SELECT * FROM mahasiswa WHERE jurusan = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, "Informatika");
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("=== MAHASISWA JURUSAN INFORMATIKA ===");
            while (rs.next()) {
                System.out.println(
                    rs.getString("nim") + " - " +
                    rs.getString("nama") + " - " +
                    rs.getString("jurusan")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
