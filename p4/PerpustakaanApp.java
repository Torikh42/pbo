package p4;

public class PerpustakaanApp {
    public static void main(String[] args) {
        // 1. Inisialisasi Perpustakaan
        Perpustakaan perpus = new Perpustakaan();

        // 2. Buat Beberapa Buku
        Buku buku1 = new Buku("Pemrograman Java", "Budi");
        Buku buku2 = new Buku("Struktur Data", "Siti");
        Buku buku3 = new Buku("Algoritma", "Andi");

        // 3. Tambahkan Buku ke Perpustakaan
        perpus.tambahBuku(buku1);
        perpus.tambahBuku(buku2);
        perpus.tambahBuku(buku3);

        // 4. Tampilkan Daftar Buku
        perpus.tampilkanDaftarBuku();

        // 5. Buat Anggota
        Anggota anggota1 = new Anggota("Torikh");

        // 6. Anggota Meminjam Buku
        System.out.println("\n--- Simulasi Peminjaman ---");
        anggota1.pinjamBuku(buku1);
        anggota1.pinjamBuku(buku2);

        // 7. Tampilkan Daftar Buku Setelah Dipinjam
        perpus.tampilkanDaftarBuku();
        anggota1.tampilkanBukuDipinjam();

        // 8. Anggota Mengembalikan Buku
        System.out.println("\n--- Simulasi Pengembalian ---");
        anggota1.kembalikanBuku(buku1);

        // 9. Tampilkan Daftar Buku Setelah Dikembalikan
        perpus.tampilkanDaftarBuku();
        anggota1.tampilkanBukuDipinjam();
    }
}
