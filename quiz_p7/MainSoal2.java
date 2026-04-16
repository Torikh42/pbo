package quiz_p7;

public class MainSoal2 {
    public static void main(String[] args) {
        System.out.println("=== Simulasi Pertarungan ===");
        
        // Membuat minimal 2 object Character
        Character hero = new Character("Arthur", 100, 25);
        Character monster = new Character("Goblin", 60, 15);

        // Menampilkan status awal
        System.out.println(">> Status Awal:");
        hero.displayStatus();
        monster.displayStatus();

        // Simulasi interaksi antar object
        System.out.println("\n>> Pertarungan Dimulai!");
        hero.attack(monster);
        monster.displayStatus();

        System.out.println();
        monster.attack(hero);
        hero.displayStatus();

        System.out.println();
        hero.attack(monster);
        monster.displayStatus();

        System.out.println();
        // Serangan penentu (HP Goblin akan dibuat tidak negatif sesuai penerapan design)
        hero.attack(monster); 
        monster.displayStatus();
    }
}
