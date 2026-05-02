public class Soal2_Mahasiswa {
    private String nama;
    private int nim;

    public Soal2_Mahasiswa(String nama, int nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama;
        } else {
            System.out.println("Error: Nama tidak boleh kosong.");
        }
    }

    public int getNim() {
        return nim;
    }
    public void setNim(int nim) {
        if (nim > 0) {
            this.nim = nim;
        } else {
            System.out.println("Error: NIM harus bernilai positif.");
        }
    }

    public static void main(String[] args) {
        Soal2_Mahasiswa mhs1 = new Soal2_Mahasiswa("Torikh", 241051203);
        
        System.out.println("--- Data Awal ---");
        System.out.println("Nama : " + mhs1.getNama());
        System.out.println("NIM  : " + mhs1.getNim());

        System.out.println("\n--- Uji Validasi Setter ---");
        mhs1.setNama("");
        mhs1.setNim(-123);
        
        System.out.println("\n--- Data Setelah Perubahan ---");
        mhs1.setNama("Torikh Abdullah Naser");
        mhs1.setNim(241051203); 
        System.out.println("Nama : " + mhs1.getNama());
        System.out.println("NIM  : " + mhs1.getNim());
    }
}
