package p6;

class Motor extends Kendaraan {
    boolean adaBox;

    public Motor(String merk, boolean adaBox){
        super(merk);
        this.adaBox = adaBox;
    }
    
    public void infoMotor(){
        if (adaBox) 
            System.out.println("Motor " + merk + " memiliki box penyimpanan");
        else
            System.out.println("Motor " + merk + " tidak memiliki box penyimpanan.");
    }
}
