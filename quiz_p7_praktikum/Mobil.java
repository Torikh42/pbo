package quiz_p7_praktikum;

public class Mobil extends Kendaraan {
    private int jumlahPintu;

    public Mobil(String platNomor, int jumlahPintu) {
        super(platNomor, "Mobil");
        this.jumlahPintu = jumlahPintu;
    }

    public int getJumlahPintu() { return jumlahPintu; }
    public void setJumlahPintu(int jumlahPintu) { this.jumlahPintu = jumlahPintu; }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jumlah Pintu: " + jumlahPintu);
    }
}
