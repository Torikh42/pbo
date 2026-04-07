package p5;

public class MainTiket {
    public static void main(String[] args) {
        Tiket tiket = new Tiket("Konser", 500000, 100);
        boolean berhasil = tiket.pesanTiket(3);
        System.out.println("Pemesanan berhasil: " + berhasil);
        System.out.println("Sisa Kursi: " + tiket.getJumlahKursi());
    }
}
