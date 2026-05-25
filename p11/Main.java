package p11;

public class Main {
    public static void main(String[] args) {
        SistemManajemenPekerja sistem = new SistemManajemenPekerja();

        PekerjaTetap pt = new PekerjaTetap("Andi", "PT-001", 5000000);
        PekerjaLepas pl = new PekerjaLepas("Budi", "PL-001", 100000, 40);

        sistem.tambahPekerja(pt);
        sistem.tambahPekerja(pl);

        System.out.println("=== Sistem Manajemen Pekerja ===");
        sistem.kelolaPekerja();
    }
}
