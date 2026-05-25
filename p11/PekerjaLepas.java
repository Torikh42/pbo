package p11;

public class PekerjaLepas extends Pekerja {
    private double tarifPerJam;
    private int jamKerja;

    public PekerjaLepas(String nama, String id, double tarifPerJam, int jamKerja) {
        super(nama, id);
        this.tarifPerJam = tarifPerJam;
        this.jamKerja = jamKerja;
    }

    @Override
    public void laksanakanTugas() {
        System.out.println(nama + " sedang melaksanakan tugas sebagai pekerja lepas (freelancer).");
    }

    @Override
    public double hitungGaji() {
        return tarifPerJam * jamKerja;
    }
}
