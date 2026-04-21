package quiz_p7_praktikum;

public class MainSoal1 {
    public static void main(String[] args) {
        System.out.println(" === Manajemen Perpustakaan Digital ");
        
        Buku buku1 = new Buku("Laskar Pelangi", "Andrea Hirata");
        BukuElektronik bukuE = new BukuElektronik("Pemrograman Java", "Budi Raharjo", "PDF");
        
        Pengguna user1 = new Pengguna("Andi");
        Pengguna user2 = new Pengguna("Budi");
        
        System.out.println("Info Buku Awal:");
        buku1.tampilInfo();
        System.out.println();
        bukuE.tampilInfo();
        
        System.out.println("\n Andi meminjam buku fisik:");
        user1.pinjamBuku(buku1);
        
        System.out.println("\n Budi meminjam buku fisik (seharusnya gagal):");
        user2.pinjamBuku(buku1);
        
        System.out.println("\n Info buku fisik sekarang:");
        buku1.tampilInfo();
        
        System.out.println("\n Andi mengembalikan buku fisik:");
        user1.kembalikanBuku(buku1);
        
        System.out.println("\n Budi meminjam buku elektronik:");
        user2.pinjamBuku(bukuE);
        
        System.out.println("\n Info buku fisik setelah dikembalikan:");
        buku1.tampilInfo();
        System.out.println("\n Info buku elektronik sekarang:");
        bukuE.tampilInfo();
    }
}
