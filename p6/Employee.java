package p6;

public class Employee extends Person{
    private String noKaryawan;

    public Employee(String name, int age, String noKaryawan) {
        super(name, age);
        this.noKaryawan = noKaryawan;
    }

    public void info() {
        System.out.println("No Karyawan : " + this.noKaryawan);
        super.info();
    }
}
