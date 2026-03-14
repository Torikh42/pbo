public class MamaliaDarat{
    public static void main(String[] args){
    String suaraHewan = ""  ;
    
    System.out.println("\nDEMO KELAS, METHOD DAN DATA");
    System.out.println("=============================\n");

    // memanggil method mengeong() dari kelas Kucing
    Kucing pussy  = new Kucing();
    suaraHewan = pussy.mengeong();
    System.out.println("Suara Pussy: " + suaraHewan);
    
    // memanggil method menggonggong() dari kelas Anjing
    Anjing dogy = new Anjing();
    suaraHewan = dogy.menggonggong();
    System.out.println("Suara Dogy: " + suaraHewan);
    
    // memanggil method mengaum() dari kelas Macan
    Macan tiger = new Macan();
    suaraHewan = tiger.mengaum();
    System.out.println("Suara Tiger: " + suaraHewan);   
    }
}

class Kucing {
    public String mengeong() {
        String suara = new String("Meong..");
        return suara;
    }
}

class Anjing {
    public String menggonggong() {
        String suara = new String("Guk..Guk..");
        return suara;
    }
}

class Macan {
    public String mengaum() {
        String suara = new String("Auuumm..");
        return suara;
    }
}