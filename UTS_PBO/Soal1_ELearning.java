package UTS_PBO;

class User {
    private final String id;
    private String name;
    private static int totalUsers = 0;

    protected class LoginActivity {
        private String timestamp;

        public LoginActivity(String timestamp) {
            this.timestamp = timestamp;
        }

        public void recordLog() {
            System.out.println("[LOG] User " + name + " (ID: " + id + ") logged in at " + timestamp);
        }
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        totalUsers++;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public static int getTotalUsers() { return totalUsers; }

    public void login(String timestamp) {
        LoginActivity activity = new LoginActivity(timestamp);
        activity.recordLog();
    }
}

class Student extends User {
    private double assignmentScore;

    public Student(String id, String name) {
        super(id, name);
        this.assignmentScore = 0.0;
    }

    public double getAssignmentScore() {
        return assignmentScore;
    }

    public void setAssignmentScore(double score) {
        if (score >= 0 && score <= 100) {
            this.assignmentScore = score;
        } else {
            System.out.println("Error: Score harus antara 0 dan 100!");
        }
    }

    public double calculateFinalScore() {
        return this.assignmentScore;
    }
}

class Instructor extends User {
    public Instructor(String id, String name) {
        super(id, name);
    }

    public void gradeStudent(Student student, double score) {
        System.out.println("Instructor " + this.getName() + " memberikan nilai " + score + " kepada " + student.getName());
        student.setAssignmentScore(score);
    }
}

public class Soal1_ELearning {
    public static void main(String[] args) {
        System.out.println("=== SISTEM E-LEARNING ===");

        Student s1 = new Student("STU-001", "Torikh");
        Instructor i1 = new Instructor("INS-999", "Dosen PBO");

        s1.login("2026-04-23 08:00:00");
        i1.login("2026-04-23 08:05:00");

        i1.gradeStudent(s1, 85.5);

        System.out.println("Skor Akhir " + s1.getName() + " : " + s1.calculateFinalScore());

        s1.setAssignmentScore(150.0);

        System.out.println("Total user terdaftar: " + User.getTotalUsers());
    }
}
