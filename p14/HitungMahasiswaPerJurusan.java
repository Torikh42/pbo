package p14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HitungMahasiswaPerJurusan {
    public static void main(String[] args) {
        String sql = "SELECT jurusan, COUNT(*) AS total FROM mahasiswa GROUP BY jurusan";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("=== TOTAL MAHASISWA PER JURUSAN ===");
            while (rs.next()) {
                System.out.println(rs.getString("jurusan") + " = " + rs.getInt("total"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
