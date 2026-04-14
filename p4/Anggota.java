package p4;

import java.util.ArrayList;
import java.util.List;

public class Anggota {
    private String nama;
    private List<Buku> daftarPinjaman;

    public Anggota(String nama) {
        this.nama = nama;
        this.daftarPinjaman = new ArrayList<>();
    }

    public void pinjamBuku(Buku buku) {
        if (!buku.isSedangDipinjam()) {
            buku.pinjamBuku();
            daftarPinjaman.add(buku);
            System.out.println(nama + " berhasil meminjam buku: " + buku.getJudul());
        } else {
            System.out.println("Maaf, buku '" + buku.getJudul() + "' tidak tersedia.");
        }
    }

    public void kembalikanBuku(Buku buku) {
        if (daftarPinjaman.contains(buku)) {
            buku.kembalikanBuku();
            daftarPinjaman.remove(buku);
            System.out.println(nama + " berhasil mengembalikan buku: " + buku.getJudul());
        } else {
            System.out.println(nama + " tidak meminjam buku: " + buku.getJudul());
        }
    }

    public void tampilkanBukuDipinjam() {
        System.out.println("Daftar buku yang dipinjam oleh " + nama + ":");
        if (daftarPinjaman.isEmpty()) {
            System.out.println("- Tidak ada buku yang dipinjam.");
        } else {
            for (Buku buku : daftarPinjaman) {
                System.out.println("- " + buku.getJudul());
            }
        }
    }
}
