class Outer {
    private String pesan = "Hello dari Outer Class";

    class Inner {
        void tampilPesan(){
            System.out.println(pesan);
        }
    }
}

public class NonStaticInner {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.tampilPesan();
    }
}
