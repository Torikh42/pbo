package quiz_p7;

public class Character {
    private String nama;
    private int hp;
    private int attackPower;

    public Character(String nama, int hp, int attackPower) {
        this.nama = nama;
        // Pencegahan HP negatif saat inisialisasi karakter
        this.hp = Math.max(hp, 0); 
        this.attackPower = attackPower;
    }

    // Method privat untuk menangani pengurangan HP dari serangan karakter lain
    private void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0; // Solusi untuk mencegah HP menjadi negatif (Soal 2 Bagian C point 1b)
        }
    }

    public void attack(Character target) {
        if (this.hp <= 0) {
            System.out.println(this.nama + " tidak bisa menyerang karena sudah kalah.");
            return;
        }
        
        System.out.println(this.nama + " menyerang " + target.getNama() + " dengan kekuatan " + attackPower + "!");
        target.takeDamage(this.attackPower);
        
        if (target.getHp() == 0) {
            System.out.println(target.getNama() + " telah dikalahkan!");
        }
    }

    public void displayStatus() {
        System.out.println("Status " + nama + ": HP = " + hp + ", Attack Power = " + attackPower);
    }
    
    // Getter methods
    public String getNama() {
        return nama;
    }
    
    public int getHp() {
        return hp;
    }
}
