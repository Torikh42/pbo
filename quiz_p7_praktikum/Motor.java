package quiz_p7_praktikum;

public class Motor extends Kendaraan {
    private String tipe;

    public Motor(String platNomor, String tipe) {
        super(platNomor, "Motor");
        this.tipe = tipe;
    }

    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Tipe Motor: " + tipe);
    }
}
