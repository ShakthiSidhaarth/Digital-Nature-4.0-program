public class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public void setQuantity(int qty) {
        this.quantity = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void display() {
        System.out.println(productId + " | " + productName + " | Qty: " + quantity + " | Rs " + price);
    }
}
