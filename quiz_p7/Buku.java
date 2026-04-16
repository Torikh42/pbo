package quiz_p7;

public class Buku {
    private String judul;
    private String pengarang;
    private boolean tersedia;

    public Buku(String judul, String pengarang) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tersedia = true;
    }

    public void pinjamBuku() {
        if (tersedia) {
            tersedia = false;
            System.out.println("Buku '" + judul + "' berhasil dipinjam.");
        } else {
            System.out.println("Maaf, buku '" + judul + "' sedang tidak tersedia.");
        }
    }

    public void kembalikanBuku() {
        if (!tersedia) {
            tersedia = true;
            System.out.println("Buku '" + judul + "' berhasil dikembalikan.");
        } else {
            System.out.println("Buku '" + judul + "' sudah tersedia (belum dipinjam).");
        }
    }

    public void tampilInfo() {
        System.out.println("--- Informasi Buku ---");
        System.out.println("Judul: " + judul);
        System.out.println("Pengarang: " + pengarang);
        System.out.println("Status: " + (tersedia ? "Tersedia" : "Dipinjam"));
        System.out.println("----------------------");
    }
}
