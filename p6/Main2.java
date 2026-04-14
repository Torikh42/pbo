package p6;

public class Main2 {
    public static void main(String[] args) {
        BukuPelajaran b1 = new BukuPelajaran("Matematika Dasar", "Dr. Andi", "Matematika");
        Novel b2 = new Novel("Laskar Pelangi", "Andrea Hirata", "Drama");

        b1.info();
        b2.info();
    }
}
