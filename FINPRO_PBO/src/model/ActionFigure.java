package model;

public class ActionFigure extends ItemKoleksi {
    private String brand;

    public ActionFigure(String idKoleksi, String judul, double hargaSewa, int stok, String brand) {
        super(idKoleksi, judul, "Action Figure", hargaSewa, stok);
        this.brand = brand;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    @Override
    public String getAtributKhusus() {
        return brand;
    }
}
