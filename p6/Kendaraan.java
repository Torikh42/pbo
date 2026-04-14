package p6;

public class Kendaraan {
    String merk;
    public Kendaraan(String merk){
        this.merk = merk;
    }
    public void jalan(){
        System.out.println(merk + " sedang berjalan");
    }
}
