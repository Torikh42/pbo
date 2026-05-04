package p9;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Enemy> enemies = new ArrayList<>();
        
        enemies.add(new Zombie());
        enemies.add(new Pocong());
        enemies.add(new Burung());
        enemies.add(new Enemy());

        System.out.println("--- Enemy Attack Simulations ---");
        
        for (Enemy e : enemies) {
            e.attack();
        }
    }
}
