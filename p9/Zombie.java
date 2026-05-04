package p9;

public class Zombie extends Enemy {
    @Override
    public void attack() {
        System.out.println("Zombie attacks: Chomp chomp! (Eating brains)");
    }
}
