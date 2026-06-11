package model;

public class Manga extends ItemKoleksi {
    private String volume;

    public Manga(String idKoleksi, String judul, double hargaSewa, int stok, String volume) {
        super(idKoleksi, judul, "Manga", hargaSewa, stok);
        this.volume = volume;
    }

    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume; }

    @Override
    public String getAtributKhusus() {
        return volume;
    }
}
