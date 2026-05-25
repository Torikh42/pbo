package p11;

public class PekerjaTetap extends Pekerja {
    private double gajiBulanan;

    public PekerjaTetap(String nama, String id, double gajiBulanan) {
        super(nama, id);
        this.gajiBulanan = gajiBulanan;
    }

    @Override
    public void laksanakanTugas() {
        System.out.println(nama + " sedang melaksanakan tugas sebagai pekerja tetap.");
    }

    @Override
    public double hitungGaji() {
        return gajiBulanan;
    }
}
