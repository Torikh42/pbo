package p14;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LihatSemuaMahasiswa {
    public static void main(String[] args) {
        String sql = "SELECT * FROM mahasiswa";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            System.out.println("=== DATA SELURUH MAHASISWA ===");
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
