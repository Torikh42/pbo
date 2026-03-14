class Mobil {
    String merk;
    int tahun;
    
    void tampilkanInfo(){
        System.out.println("Merk: " + merk);
        System.out.println("Tahun: " + tahun);
    }
}

public class Mainmobil {
    public static void main(String[] args) {
        Mobil mobil1 = new Mobil();
        mobil1.merk = "Toyota";
        mobil1.tahun = 2022;
        mobil1.tampilkanInfo();
    }  
}