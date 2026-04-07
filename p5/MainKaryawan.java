package p5;

public class MainKaryawan {
    public static void main(String[] args) {
        Karyawan karyawan1 = new Karyawan("Budi", 30, 5000000);
        System.out.println("Nama: " + karyawan1.getNama());
        System.out.println("Usia: " + karyawan1.getUsia());
        System.out.println("Gaji: " + karyawan1.getGaji());

        karyawan1.setUsia(31);
        karyawan1.setGaji(5500000);
        System.out.println("Setelah Perubahan - Usia: " + karyawan1.getUsia());
        System.out.println("Setelah Perubahan - Gaji: " + karyawan1.getGaji());
    }
}
