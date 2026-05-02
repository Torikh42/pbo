import java.util.ArrayList;
import java.util.List;

class Buku {
    private String judul;
    private boolean isDipinjam;

    public Buku(String judul) {
        this.judul = judul;
        this.isDipinjam = false; 
    }

    public String getJudul() {
        return judul;
    }

    public boolean isDipinjam() {
        return isDipinjam;
    }

    public void setDipinjam(boolean status) {
        this.isDipinjam = status;
    }
}

public class Soal4_Perpustakaan {
    private List<Buku> daftarBuku;

    public Soal4_Perpustakaan() {
        this.daftarBuku = new ArrayList<>();
    }

    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }
    public void pinjamBuku(Buku buku) {
        if (!buku.isDipinjam()) {
            buku.setDipinjam(true);
            System.out.println("-> Berhasil meminjam buku: " + buku.getJudul());
        } else {
            System.out.println("-> Gagal: Buku " + buku.getJudul() + " sedang dipinjam.");
        }
    }

    public int hitungJumlahPinjaman() {
        int jumlahPinjaman = 0;
        for (Buku buku : daftarBuku) {
            if (buku.isDipinjam()) {
                jumlahPinjaman++;
            }
        }
        return jumlahPinjaman;
    }

    public void tampilkanLaporan() {
        System.out.println("\n=== LAPORAN PERPUSTAKAAN DIGITAL ===");
        System.out.println("Total Buku Terdaftar : " + daftarBuku.size());
        
        System.out.println("Total Buku Dipinjam  : " + hitungJumlahPinjaman());
        System.out.println("Daftar Buku:");
        for (Buku buku : daftarBuku) {
            String status = buku.isDipinjam() ? "[Dipinjam]" : "[Tersedia]";
            System.out.println("- " + buku.getJudul() + " " + status);
        }
        System.out.println("====================================\n");
    }

    public static void main(String[] args) {
        Soal4_Perpustakaan perpus = new Soal4_Perpustakaan();

        Buku buku1 = new Buku("Pemrograman Java OOP");
        Buku buku2 = new Buku("Struktur Data & Algoritma");
        Buku buku3 = new Buku("Basis Data Relasional");

        perpus.tambahBuku(buku1);
        perpus.tambahBuku(buku2);
        perpus.tambahBuku(buku3);

        perpus.pinjamBuku(buku1);
        perpus.pinjamBuku(buku3);
        perpus.pinjamBuku(buku1); 

        perpus.tampilkanLaporan();
    }
}
