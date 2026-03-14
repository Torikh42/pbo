public class Product {
    private int categoryId;

    public Product(int categoryId){
        this.categoryId = categoryId;
    }

    public boolean isElectronics(){
        return categoryId == ProductCategory.ELECTRONICS_ID;
    }

    public String getCategoryName(){
        switch (categoryId) {
            case ProductCategory.ELECTRONICS_ID:
                return "Electronics";
            case ProductCategory.CLOTHING_ID:
                return "Clothing";
            case ProductCategory.GROCERY_ID:
                return "Grocery";
            default:
                return "Unknown Category";
        }
    }
}
