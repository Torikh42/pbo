class LuarLocal {
    void tampilkan(){
        class LocalInner {
            void show(){
                System.out.println("Ini local inner class di dalam method tampilkan()");
            }
        }
        LocalInner inner = new LocalInner();
        inner.show();
    }
}


public class LocalInner {
    public static void main(String[] args) {
        LuarLocal obj = new LuarLocal();
        obj.tampilkan();
    }
}
