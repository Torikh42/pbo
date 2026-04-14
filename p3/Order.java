public class Order {
    private double basePrice;

    public Order(double basePrice) {
        this.basePrice = basePrice;
    }

    public double calculateTotal() {
        double discountedPrice = basePrice * (1 - EcommerceConstants.DISCOUNT_RATE);
        double totalPrice = discountedPrice * (1 + EcommerceConstants.TAX_RATE);
        return totalPrice;
    }
}
