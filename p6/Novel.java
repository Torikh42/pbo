package p6;

public class Novel extends Buku {
    String genre;
    public Novel(String judul, String penulis, String genre){
        super(judul, penulis);
        this.genre = genre;
    }
    @Override
    public void info(){
        System.out.println("Novel - Judul: " + judul + ", Penulis: " + penulis + ", Genre: " + genre);
    }
}
