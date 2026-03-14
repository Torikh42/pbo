public class MainProduct {
    public static void main(String[] args) {
        Product product = new Product(ProductCategory.ELECTRONICS_ID);
        System.out.println("Category Name: " + product.getCategoryName());
        System.out.println("Is Electronics? " + product.isElectronics());

    }
}
