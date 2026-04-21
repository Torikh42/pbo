package quiz_p7_praktikum;

public class Pengguna {
    private String nama;

    public Pengguna(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void pinjamBuku(Buku buku) {
        buku.pinjam(this.nama);
    }

    public void kembalikanBuku(Buku buku) {
        buku.kembalikan();
    }
}
