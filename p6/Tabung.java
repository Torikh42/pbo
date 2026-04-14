package p6;

public class Tabung extends Lingkaran {
    private double tinggi;

    public Tabung(double r, double tinggi) {
        super(r);
        this.tinggi = tinggi;
    }

    public double getVolume() {
        return getLuas() * tinggi;
    }

    public void tampilkanTabung() {
        System.out.println("Tabung");
        System.out.println("Jari-jari : " + r);
        System.out.println("Tinggi : " + tinggi);
        System.out.println("Luas Lingkaran Dasar : " + getLuas());
        System.out.println("Volume : " + getVolume());
    }
}
