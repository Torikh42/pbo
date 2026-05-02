public class Soal1_Karyawan {
    private String idKaryawan;
    private String nama;
    private String jabatan;
    private double gajiPokok;

    public Soal1_Karyawan(String idKaryawan, String nama, String jabatan, double gajiPokok) {
        this.idKaryawan = idKaryawan;
        this.nama = nama;
        this.jabatan = jabatan;
        this.gajiPokok = gajiPokok;
    }

    public void tampilkanInfo() {
        System.out.println("ID Karyawan : " + this.idKaryawan);
        System.out.println("Nama        : " + this.nama);
        System.out.println("Jabatan     : " + this.jabatan);
        System.out.println("Gaji Pokok  : Rp" + this.gajiPokok);
        System.out.println("-------------------------------------------------");
    }

    public double hitungGajiTotal() {
        double tunjangan = 0.1 * this.gajiPokok;
        return this.gajiPokok + tunjangan;
    }

    public static void main(String[] args) {
        System.out.println("====== SISTEM INFORMASI MANAJEMEN KARYAWAN ======\n");

        Soal1_Karyawan karyawan1 = new Soal1_Karyawan("K001", "Budi Santoso", "Manager", 8000000);
        Soal1_Karyawan karyawan2 = new Soal1_Karyawan("K002", "Siti Aminah", "Staff IT", 5000000);

        System.out.println("--- Data Karyawan ---");
        karyawan1.tampilkanInfo();
        karyawan2.tampilkanInfo();
        
        System.out.println("\n--- Perhitungan Gaji Pokok + Tunjangan ---");
        System.out.println("Total Gaji " + karyawan1.nama + " : Rp" + karyawan1.hitungGajiTotal());
        System.out.println("Total Gaji " + karyawan2.nama + " : Rp" + karyawan2.hitungGajiTotal());
    }
}
