package UTS_PBO;

class UserRefactored {
    private final String id;
    private String name;
    private int score;

    protected class ActivityLog {
        public void log(String action) {
            System.out.println("[SYSTEM LOG] " + name + " (ID: " + id + ") -> " + action);
        }
    }

    public UserRefactored(String id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getScore() { return score; }

    public void setScore(int score) {
        if (score >= 0 && score <= 100) {
            this.score = score;
            new ActivityLog().log("Score diubah menjadi " + score);
        } else {
            System.out.println("Error: Score harus antara 0 dan 100!");
        }
    }

    public void print() {
        System.out.println(name + " : " + score);
    }

    protected ActivityLog getLogger() {
        return new ActivityLog();
    }
}

class StudentRefactored extends UserRefactored {

    public StudentRefactored(String id, String name, int score) {
        super(id, name, score);
    }

    public void calculate() {
        int newScore = getScore() + 10;
        setScore(newScore);
    }
}

public class Soal2_Refactored {
    public static void main(String[] args) {
        System.out.println("=== SISTEM REFACTORING SOAL 2 ===");

        StudentRefactored student = new StudentRefactored("2410512031", "Torikh Abdullah Naser", 80);

        student.print();
        student.calculate();
        student.print();
    }
}
