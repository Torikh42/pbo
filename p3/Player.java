public class Player {
    private int lives;
    private int level;

    public Player(){
        this.lives = GameConstants.MAX_LIVES;
        this.level = 1;
    }

    public boolean canLevelUp(){
        return level < GameConstants.MAX_LEVEL;
    }

    public void LevelUp(){
        if (canLevelUp()) {
            level++;
            System.out.println("Level up! current level: " + level);
        } else {
            System.out.println("Reached max level!");
        }
    }

    public void loseLife(){
        if (lives > 0) {
            lives--;
            System.out.println("Life lost! Remaining lives: " + lives);
        } else {
            System.out.println("Game Over!");
        }
    }

}
