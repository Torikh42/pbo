package quiz_p7;

public class MainSoal1 {
    public static void main(String[] args) {
        System.out.println("=== Simulasi Sistem Perpustakaan ===");
        
        // Membuat 2 object Buku
        Buku buku1 = new Buku("Laskar Pelangi", "Andrea Hirata");
        Buku buku2 = new Buku("Bumi Manusia", "Pramoedya Ananta Toer");

        // Menampilkan info awal
        buku1.tampilInfo();
        buku2.tampilInfo();

        // Simulasi peminjaman dan pengembalian
        System.out.println("\n>> Meminjam buku1:");
        buku1.pinjamBuku();
        
        System.out.println("\n>> Mencoba meminjam buku1 lagi:");
        buku1.pinjamBuku();

        System.out.println("\n>> Menampilkan info setelah buku1 dipinjam:");
        buku1.tampilInfo();

        System.out.println("\n>> Mengembalikan buku1:");
        buku1.kembalikanBuku();
        
        System.out.println("\n>> Menampilkan info setelah buku1 dikembalikan:");
        buku1.tampilInfo();
    }
}
