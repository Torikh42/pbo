public class Laptop {
  String merk;
  int ram;
  String memori;
  int harga;

  public static void main(String[] args) {
    Laptop laptop1 = new Laptop();
    laptop1.merk = "asus";
    laptop1.ram = 8;
    laptop1.memori = "512gb";
    laptop1.harga = 2000000;
    System.out.printf("merk: %s\nram: %d\nmemori: %s\nharga: %d", laptop1.merk, laptop1.ram, laptop1.memori, laptop1.harga);
  }
}