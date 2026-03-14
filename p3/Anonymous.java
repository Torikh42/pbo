class Hewan{
    void suara(){
        System.out.println("hewan mengeluarkan suara...");
    }
}

public class Anonymous {
    public static void main(String[] args) {
        Hewan kucing = new Hewan(){
            @Override
            void suara(){
                System.out.println("meong! (override dari anonymous inner class)");
            }
        };
        kucing.suara();
    }
}
