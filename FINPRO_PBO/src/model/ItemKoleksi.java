package model;

public abstract class ItemKoleksi {
    protected String idKoleksi;
    protected String judul;
    protected String kategori;
    protected double hargaSewa;
    protected int stok;

    public ItemKoleksi(String idKoleksi, String judul, String kategori, double hargaSewa, int stok) {
        this.idKoleksi = idKoleksi;
        this.judul = judul;
        this.kategori = kategori;
        this.hargaSewa = hargaSewa;
        this.stok = stok;
    }

    public String getIdKoleksi() { return idKoleksi; }
    public void setIdKoleksi(String idKoleksi) { this.idKoleksi = idKoleksi; }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public double getHargaSewa() { return hargaSewa; }
    public void setHargaSewa(double hargaSewa) { this.hargaSewa = hargaSewa; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public abstract String getAtributKhusus();
}
