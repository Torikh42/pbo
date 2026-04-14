package p6;

public class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void info() {
        System.out.println("Nama : " + this.name);
        System.out.println("Usia : " + this.age);
    }
}
