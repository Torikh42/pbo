public class EcommerceApp {
    public static void main(String[] args) {
        // Misalkan harga dasar adalah 100 agar menghasilkan output 97.2
        double basePrice = 100.0;
        Order order = new Order(basePrice);
        double totalPrice = order.calculateTotal();

        System.out.println("Total Price after tax and discount : Rp. " + String.format("%.4f", totalPrice));
    }
}
