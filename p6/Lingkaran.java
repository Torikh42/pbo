package p6;

public class Lingkaran {
    protected double r;

    public Lingkaran(double r) {
        this.r = r;
    }

    public double getLuas() {
        return Math.PI * r * r;
    }

    public double getKeliling() {
        return 2 * Math.PI * r;
    }

    public void tampilkanLingkaran() {
        System.out.println("Lingkaran :");
        System.out.println("Jari-jari : " + r);
        System.out.println("Luas : " + getLuas());
        System.out.println("Keliling : " + getKeliling());
    }
}
