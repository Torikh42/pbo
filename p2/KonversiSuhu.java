public class KonversiSuhu {
    public double celcius;
    public double fahrenheit;

    public double toFahrenheit() {
        return (celcius * 9 / 5) + 32;
    }

    public double toCelcius() {
        return (fahrenheit - 32) * 5 / 9;
    }
}
