package p5;

class Tiket {
    private String jenisTiket;
    private double hargaTiket;
    private int jumlahKursi;
    
    public Tiket(String jenisTiket, double hargaTiket, int jumlahKursi){
        this.jenisTiket = jenisTiket;
        this.hargaTiket = hargaTiket;
        this.jumlahKursi = jumlahKursi;
    }

    public String getJenisTiket(){
        return jenisTiket;
    }

    public double getHargaTiket(){
        return hargaTiket;
    }

    public int getJumlahKursi(){
        return jumlahKursi;
    }

    public boolean pesanTiket(int jumlah){
        if(jumlah > 0 && jumlah <= jumlahKursi){
            jumlahKursi -= jumlah;
            return true;
        }
        return false;
    }
}
