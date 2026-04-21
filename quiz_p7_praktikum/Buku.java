package quiz_p7_praktikum;

public class Buku {
    private String judul;
    private String penulis;
    private boolean tersedia = true;
    private RiwayatPeminjaman riwayat;

    public Buku(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
        this.riwayat = new RiwayatPeminjaman();
    }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getPenulis() { return penulis; }
    public void setPenulis(String penulis) { this.penulis = penulis; }

    public boolean isTersedia() { return tersedia; }
    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }
    
    public RiwayatPeminjaman getRiwayat() { return riwayat; }

    public void pinjam(String namaPeminjam) {
        if (this.tersedia) {
            this.tersedia = false;
            this.riwayat.setNamaPeminjamTerakhir(namaPeminjam);
            System.out.println("Buku '" + this.judul + "' berhasil dipinjam oleh " + namaPeminjam);
        } else {
            System.out.println("Buku '" + this.judul + "' sedang tidak tersedia (sedang dipinjam oleh " + this.riwayat.getNamaPeminjamTerakhir() + ").");
        }
    }

    public void kembalikan() {
        if (!this.tersedia) {
            this.tersedia = true;
            System.out.println("Buku '" + this.judul + "' berhasil dikembalikan.");
        } else {
            System.out.println("Buku '" + this.judul + "' sudah tersedia.");
        }
    }

    public void tampilInfo() {
        System.out.println("Judul: " + judul + ", Penulis: " + penulis + ", Tersedia: " + (tersedia ? "Ya" : "Tidak"));
        System.out.println("Peminjam Terakhir: " + riwayat.getNamaPeminjamTerakhir());
    }

    // inner classnya riwayat peminjeman
    public class RiwayatPeminjaman {
        private String namaPeminjamTerakhir = "Belum ada";

        public String getNamaPeminjamTerakhir() {
            return namaPeminjamTerakhir;
        }

        public void setNamaPeminjamTerakhir(String namaPeminjamTerakhir) {
            this.namaPeminjamTerakhir = namaPeminjamTerakhir;
        }
    }
}
