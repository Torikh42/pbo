package p4;

class Kalkulator {
    int tambah(int a, int b){
        return a+b;
    }
    public static void main(String[] args) {
        Kalkulator k = new Kalkulator();
        int hasil = k.tambah(5, 7);
        System.out.println("Hasil Penjumlahan: " + hasil);
    }
}
