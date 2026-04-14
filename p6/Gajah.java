package p6;

public class Gajah extends Hewan {
    public static void testClassMethod(){
        System.out.println("The Class Method in Hewan....");
    }

    public void testInstanceMethod(){
        System.out.println("The Instance Method in Gajah....");
    }

    public static void main(String[] args) {
        // Kucing myGajah = new Gajah(); salah
        // Hewan myHewan = myGajah; salah
        Gajah myGajah = new Gajah();
        Hewan myHewan = new Hewan();
        Hewan.testClassMethod();
        Gajah.testClassMethod();
        myHewan.testInstanceMethod();
        myGajah.testInstanceMethod();
    }
}
