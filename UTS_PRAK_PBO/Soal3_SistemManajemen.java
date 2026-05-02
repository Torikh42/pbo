class Soal3_Person {
    protected String nama;
    protected int umur;

    public Soal3_Person(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    public void tampilkanInfo() {
        System.out.println("Nama : " + this.nama);
        System.out.println("Umur : " + this.umur + " tahun");
    }
}

class Soal3_Employee extends Soal3_Person {
    private String idKaryawan;

    public Soal3_Employee(String nama, int umur, String idKaryawan) {
        super(nama, umur);
        this.idKaryawan = idKaryawan;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo(); 
        System.out.println("ID   : " + this.idKaryawan);
    }
}

public class Soal3_SistemManajemen {
    public static void main(String[] args) {
        System.out.println("=== Data Person ===");
        Soal3_Person p1 = new Soal3_Person("Agus", 45);
        p1.tampilkanInfo(); 

        System.out.println("\n=== Data Employee ===");
        Soal3_Employee e1 = new Soal3_Employee("Torikh", 20, "EMP-2026-001");
        e1.tampilkanInfo();
    }
}