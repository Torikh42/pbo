package p6;

public class Hasil {
    public static void main(String[] args) {
        Lingkaran lingkaran = new Lingkaran(5.0);
        lingkaran.tampilkanLingkaran();

        System.out.println();

        Tabung tabung = new Tabung(3.0, 4.0);
        tabung.tampilkanTabung();
    }
}
