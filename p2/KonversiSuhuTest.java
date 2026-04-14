public class KonversiSuhuTest {
    public static void main(String[] args) {
        KonversiSuhu ksuhu = new KonversiSuhu();

        ksuhu.celcius = 100.0;
        System.out.println(ksuhu.celcius + " Celcius = " + ksuhu.toFahrenheit() + " Fahrenheit");

        ksuhu.fahrenheit = 212.0;
        System.out.println(ksuhu.fahrenheit + " Fahrenheit = " + ksuhu.toCelcius() + " Celcius");
    }
}
