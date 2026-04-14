package p5;

public class Restoran {
    public static void main(String[] args) {
        Makanan makanan1 = new Makanan();
        
        makanan1.setMenu("Ayam Goreng");
        makanan1.setHarga(170000);
        makanan1.setSpesial(true);

        System.out.println("Menu Makanan  : " + makanan1.getMenu());
        System.out.println("Harga Makanan : Rp. " + (int)makanan1.getHarga());
        System.out.println("Menu Spesial  : " + makanan1.isSpesial());
    }
}
