public class TipeData {
    public static void main(String[] args) {
        int result = penjumlahan(10, 20);
        System.out.println(result);

        System.out.println(penjumlahan(100, result));
    }

    static int penjumlahan(int angka1, int angka2){
        int hasil = angka1 + angka2;
        return hasil;
    }

    
}
