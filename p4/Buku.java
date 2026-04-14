package p4;

public class Buku {
    private String judul;
    private String penulis;
    private boolean sedangDipinjam;

    public Buku(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
        this.sedangDipinjam = false;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public boolean isSedangDipinjam() {
        return sedangDipinjam;
    }

    public void pinjamBuku() {
        if (!sedangDipinjam) {
            sedangDipinjam = true;
        } else {
            System.out.println("Buku '" + judul + "' sudah dipinjam.");
        }
    }

    public void kembalikanBuku() {
        sedangDipinjam = false;
    }

    @Override
    public String toString() {
        return "Buku [Judul: " + judul + ", Penulis: " + penulis + ", Status: " + (sedangDipinjam ? "Dipinjam" : "Tersedia") + "]";
    }
}
