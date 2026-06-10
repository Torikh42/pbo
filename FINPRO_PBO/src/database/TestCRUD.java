package database;

import java.time.LocalDate;

public class TestCRUD {
    public static void main(String[] args) {
        System.out.println("=== MEMULAI TEST CRUD BACKEND (ANGGOTA 2) ===");

        // 1. Test Koneksi
        System.out.println("\n[1] Mengetes Koneksi Database...");
        Koneksi.main(new String[]{});

        // 2. Setup Database (Bersihkan dan Seed data awal)
        System.out.println("\n[2] Menjalankan Setup & Seeding Database...");
        SetupDatabase.main(new String[]{});

        // 3. Menampilkan Data Awal
        System.out.println("\n[3] Data Awal Database:");
        DbCRUD.listAllUsers();
        DbCRUD.listAllKoleksi();
        DbCRUD.listAllRental();

        // 4. Test User CRUD
        System.out.println("\n[4] Mengetes Fitur Login & User...");
        boolean loginAdmin = DbCRUD.verifyLogin("admin", "admin123");
        boolean loginSalah = DbCRUD.verifyLogin("admin", "salahpassword");
        System.out.println("Login admin (seharusnya true): " + loginAdmin);
        System.out.println("Login salah (seharusnya false): " + loginSalah);
        
        System.out.println("Menambahkan user baru...");
        boolean tambahUser = DbCRUD.addUser("kasir", "kasir123", "Staff");
        System.out.println("Tambah user (seharusnya true): " + tambahUser);
        DbCRUD.listAllUsers();

        // 5. Test Koleksi CRUD
        System.out.println("\n[5] Mengetes Fitur CRUD Koleksi...");
        System.out.println("Memasukkan item baru...");
        boolean tambahKoleksi = DbCRUD.insertKoleksi("MGA003", "Attack on Titan Vol 1", "Manga", 4500.0, 10, "Vol 1");
        System.out.println("Tambah koleksi baru (seharusnya true): " + tambahKoleksi);
        
        System.out.println("Mengubah data item...");
        boolean ubahKoleksi = DbCRUD.updateKoleksi("MGA003", "Attack on Titan Vol 1 (Special Edition)", 5000.0, 8, "Vol 1 SE");
        System.out.println("Update koleksi (seharusnya true): " + ubahKoleksi);
        
        System.out.println("Menghapus item...");
        boolean hapusKoleksi = DbCRUD.deleteKoleksi("MGA002"); // Hapus Naruto Vol 72
        System.out.println("Hapus koleksi Naruto (seharusnya true): " + hapusKoleksi);
        DbCRUD.listAllKoleksi();

        // 6. Test Transaksi Rental (Peminjaman & Pengembalian)
        System.out.println("\n[6] Mengetes Fitur Transaksi Rental...");
        
        // Coba pinjam item yang stoknya ada
        System.out.println("Budi meminjam FIG002 (stok awal 1)...");
        boolean pinjam1 = DbCRUD.pinjamKoleksi("Budi", "FIG002", LocalDate.of(2026, 6, 10));
        System.out.println("Pinjam 1 (seharusnya true): " + pinjam1);
        
        // Coba pinjam item yang stoknya sudah habis (FIG002 harusnya sekarang stok 0)
        System.out.println("Ciko mencoba meminjam FIG002 lagi (stok sekarang 0)...");
        boolean pinjam2 = DbCRUD.pinjamKoleksi("Ciko", "FIG002", LocalDate.of(2026, 6, 10));
        System.out.println("Pinjam 2 (seharusnya false karena stok kosong): " + pinjam2);
        
        DbCRUD.listAllKoleksi(); // Lihat stok FIG002 berkurang jadi 0
        DbCRUD.listAllRental();  // Lihat transaksi peminjaman baru ditambahkan

        // Kembalikan item dan hitung biaya
        System.out.println("\nBudi mengembalikan FIG002 pada tanggal 2026-06-15...");
        // id_rental untuk peminjaman baru Budi seharusnya ID 3 (karena data dummy ID 1, 2)
        boolean kembali = DbCRUD.kembalikanKoleksi(3, LocalDate.of(2026, 6, 15));
        System.out.println("Kembali (seharusnya true): " + kembali);
        
        DbCRUD.listAllKoleksi(); // Lihat stok FIG002 kembali jadi 1
        DbCRUD.listAllRental();  // Lihat transaksi status berubah jadi 'Dikembalikan' dan biaya terhitung (5 hari * 50000 = 250000)

        System.out.println("\n=== TESTING SELESAI ===");
    }
}
