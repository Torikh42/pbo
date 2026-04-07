package p5;

class Karyawan {
    private String nama;
    private int usia;
    private double gaji;    

    public Karyawan(String nama, int usia, double gaji){
        this.nama = nama;
        this.usia = usia;
        this.gaji = gaji;
    }

    public String getNama(){
        return nama;
    }

    public int getUsia(){
        return usia;
    }

    public double getGaji(){
        return gaji;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setUsia(int usia){
        if (usia > 0){
            this.usia = usia;
        }
    }

    public void setGaji(double gaji){
        if (gaji > 0){
            this.gaji = gaji;
        }   
    }
}


