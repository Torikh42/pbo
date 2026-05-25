package p11;

public abstract class Pekerja implements Tugas {
    protected String nama;
    protected String id;

    public Pekerja(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    // Mendeklarasikan metode abstrak untuk menghitung gaji (Sesuai dengan instruksi b)
    @Override
    public abstract double hitungGaji();
}
