package lkp14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SistemKRS {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int menu = 0;
        do {
            System.out.println("\n=== SISTEM MANAJEMEN KRS ===");
            System.out.println("1. Lihat Semua Mata Kuliah");
            System.out.println("2. Tambahkan Mata Kuliah ke KRS");
            System.out.println("3. Hapus Mata Kuliah dari KRS");
            System.out.println("4. Lihat KRS Mahasiswa");
            System.out.println("5. Hitung Total SKS Mahasiswa");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            try {
                menu = Integer.parseInt(scanner.nextLine());
                switch (menu) {
                    case 1 -> lihatMataKuliah();
                    case 2 -> tambahKRS();
                    case 3 -> hapusKRS();
                    case 4 -> lihatKRS();
                    case 5 -> hitungSKS();
                    case 0 -> System.out.println("Terima kasih telah menggunakan sistem ini.");
                    default -> System.out.println("Menu tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukan harus berupa angka!");
            }
        } while (menu != 0);
    }

    private static void lihatMataKuliah() {
        String sql = "SELECT * FROM matakuliah";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            System.out.println("\n--- DAFTAR MATA KULIAH ---");
            System.out.printf("%-10s | %-35s | %s\n", "Kode MK", "Nama Mata Kuliah", "SKS");
            System.out.println("---------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-10s | %-35s | %d\n", 
                    rs.getString("kode_mk"), 
                    rs.getString("nama_mk"), 
                    rs.getInt("sks"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void tambahKRS() {
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scanner.nextLine();
        System.out.print("Masukkan Kode Mata Kuliah: ");
        String kodeMk = scanner.nextLine();

        String sql = "INSERT INTO krs (nim, kode_mk) VALUES (?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, nim);
            pstmt.setString(2, kodeMk);
            pstmt.executeUpdate();
            System.out.println("Berhasil menambahkan mata kuliah ke KRS!");
        } catch (Exception e) {
            System.out.println("Gagal! Pastikan NIM terdaftar di tabel mahasiswa dan Kode MK tersedia.");
        }
    }

    private static void hapusKRS() {
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scanner.nextLine();
        System.out.print("Masukkan Kode Mata Kuliah yang akan dihapus: ");
        String kodeMk = scanner.nextLine();

        String sql = "DELETE FROM krs WHERE nim = ? AND kode_mk = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, nim);
            pstmt.setString(2, kodeMk);
            int affected = pstmt.executeUpdate();
            
            if (affected > 0) {
                System.out.println("Berhasil menghapus mata kuliah dari KRS!");
            } else {
                System.out.println("Data tidak ditemukan di KRS.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void lihatKRS() {
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scanner.nextLine();

        String sql = """
            SELECT m.nama, mk.kode_mk, mk.nama_mk, mk.sks 
            FROM krs k 
            JOIN mahasiswa m ON k.nim = m.nim 
            JOIN matakuliah mk ON k.kode_mk = mk.kode_mk 
            WHERE k.nim = ?
        """;
        
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, nim);
            ResultSet rs = pstmt.executeQuery();
            
            boolean first = true;
            System.out.println("\n--- KARTU RENCANA STUDI ---");
            
            while (rs.next()) {
                if (first) {
                    System.out.println("Nama Mahasiswa: " + rs.getString("nama"));
                    System.out.println("---------------------------------------------------------");
                    System.out.printf("%-10s | %-35s | %s\n", "Kode MK", "Nama Mata Kuliah", "SKS");
                    System.out.println("---------------------------------------------------------");
                    first = false;
                }
                System.out.printf("%-10s | %-35s | %d\n", 
                    rs.getString("kode_mk"), 
                    rs.getString("nama_mk"), 
                    rs.getInt("sks"));
            }
            if (first) {
                System.out.println("Belum ada mata kuliah yang diambil atau NIM tidak valid.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void hitungSKS() {
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scanner.nextLine();

        String sql = """
            SELECT SUM(mk.sks) AS total_sks 
            FROM krs k 
            JOIN matakuliah mk ON k.kode_mk = mk.kode_mk 
            WHERE k.nim = ?
        """;
        
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, nim);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int totalSks = rs.getInt("total_sks");
                System.out.println("Total SKS yang diambil oleh NIM " + nim + " adalah: " + totalSks + " SKS.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
