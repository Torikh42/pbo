public class Example {
    static int count = 0;
    
    final String name;

    public Example(String name){
        count++;
        this.name = name;
    }

    public static void main(String[] args) {
        Example obj1 = new Example("john");
        Example obj2 = new Example("Jane");

        System.out.println("Total objek: " + Example.count);
        System.out.println("Nama: " + obj1.name);
        System.out.println("Nama: " + obj2.name);


    }
}
