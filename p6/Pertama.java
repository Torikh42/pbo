package p6;

public class Pertama {
    private int a = 10;
    protected void terproteksi() {System.out.println("method hanya untuk anaknya");}
    public void info(){
        System.out.println("a = "+a);
        System.out.println("Dipanggil paada = "+this.getClass());
    }
}

