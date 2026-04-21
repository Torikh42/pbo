package quiz_p7_praktikum;

public class MainSoal2 {
    public static void main(String[] args) {
        System.out.println("=== Manajemen Kendaraan Parkir ===");
        
        Mobil mobil1 = new Mobil("B 1234 CD", 4);
        Motor motor1 = new Motor("D 5678 EF", "Matic");
        
        System.out.println("\nInfo Kendaraan 1:");
        mobil1.tampilkanInfo();
        
        System.out.println("\nInfo Kendaraan 2:");
        motor1.tampilkanInfo();
    }
}
