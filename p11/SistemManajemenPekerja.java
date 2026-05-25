package p11;

import java.util.ArrayList;
import java.util.List;

public class SistemManajemenPekerja {
    private List<Pekerja> daftarPekerja;

    public SistemManajemenPekerja() {
        daftarPekerja = new ArrayList<>();
    }

    public void tambahPekerja(Pekerja pekerja) {
        daftarPekerja.add(pekerja);
    }

    public void kelolaPekerja() {
        for (Pekerja pekerja : daftarPekerja) {
            System.out.println("ID: " + pekerja.getId() + " | Nama: " + pekerja.getNama());
            pekerja.laksanakanTugas();
            System.out.println("Gaji: Rp" + pekerja.hitungGaji());
            System.out.println("-------------------------------------------------");
        }
    }
}
