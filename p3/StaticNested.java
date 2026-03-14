class Luar {
    static String pesan = "ini pesan dari Static Nested Class";
    static class Dalam {
        void tampil(){
            //bisa langsung akses static member dari outer class
            System.out.println(pesan);
        }
    }    
}

public class StaticNested {
    public static void main(String[] args) {
        Luar.Dalam nested = new Luar.Dalam();
        nested.tampil();
    }
}
