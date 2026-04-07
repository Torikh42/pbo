package p4;

class Mobil {
    String merk;
    
    Mobil(String merk) {
        this.merk = merk;
    }
    
    void tampilkanInfo(){
        System.out.println("Mobil ini adalah " + merk);
    }
    public static void main(String[] args) {
        Mobil mobil = new Mobil("toyota");
        mobil.tampilkanInfo();
    }
}
