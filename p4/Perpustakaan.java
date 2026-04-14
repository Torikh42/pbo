package p4;

import java.util.ArrayList;
import java.util.List;

public class Perpustakaan {
    private List<Buku> koleksiBuku;

    public Perpustakaan() {
        this.koleksiBuku = new ArrayList<>();
    }

    public void tambahBuku(Buku buku) {
        koleksiBuku.add(buku);
        System.out.println("Buku baru ditambahkan: " + buku.getJudul());
    }

    public void tampilkanDaftarBuku() {
        System.out.println("--- Daftar Koleksi Buku Perpustakaan ---");
        if (koleksiBuku.isEmpty()) {
            System.out.println("Tidak ada buku di perpustakaan.");
        } else {
            for (Buku buku : koleksiBuku) {
                System.out.println(buku);
            }
        }
    }
}
