package p4;

public class MethodReturn {
    public static String nama = "Damas Amirul Karim";
    public static String getNama(){
        return nama;
    }
    public static int hitungUmur(int lahir, int sekarang){
        int umur = sekarang - lahir;
        return umur;
    }
    public static void main(String[] args) {
        String namaSaya = getNama();
        System.out.println("Nama saya: " + namaSaya);
        int umurSaya = hitungUmur(1995, 2019);
        System.out.println("Umur saya: " + umurSaya);
    }
}
