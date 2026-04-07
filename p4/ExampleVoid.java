package p4;

public class ExampleVoid {
    public static void main(String[] args) {
        methodRankPoints(23.3);
    }
    public static void methodRankPoints(double points) {
        if (points >= 202.5) {
            System.out.println("Rank:A1");
        } else if (points >= 122.4) {
            System.out.println("Rank:A2");
        } else {
            System.out.println("Rank:A3");
        }
    }
}
