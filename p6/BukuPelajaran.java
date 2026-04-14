package p6;

public class BukuPelajaran extends Buku {
    String mataPelajaran;

    public BukuPelajaran(String judul, String penulis, String mataPelajaran){
        super(judul, penulis);
        this.mataPelajaran = mataPelajaran;
    }
    @Override
    public void info(){
        System.out.println("Buku Pelajaran - Judul: " + judul + ", Penulis: " + penulis + ", Mapel: " + mataPelajaran);
    }
}
