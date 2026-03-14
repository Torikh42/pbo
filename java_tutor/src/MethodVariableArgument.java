public class MethodVariableArgument {
    public static void main(String[] args) {
        
    }
    static void sayCongrats(String name, int[] values){
        var total = 0;
        for( var value : values){
            total += value;
        }
        System.out.println("Selamat " + name + " nilai kamu adalah " + total);
    }
}
