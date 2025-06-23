import java.util.HashMap;

public class InventoryManager {
    private HashMap<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product already exists. Use update instead.");
            return;
        }
        inventory.put(product.getProductId(), product);
        System.out.println("Product added: " + product.getProductId());
    }

    public void updateProduct(String productId, String name, int quantity, double price) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setProductName(name);
            product.setQuantity(quantity);
            product.setPrice(price);
            System.out.println("Product updated: " + productId);
        } else {
            System.out.println("Product not found: " + productId);
        }
    }

    public void deleteProduct(String productId) {
        if (inventory.remove(productId) != null) {
            System.out.println("Product deleted: " + productId);
        } else {
            System.out.println("Product not found: " + productId);
        }
    }

    public void displayAll() {
        System.out.println("\n--- Inventory ---");
        for (Product p : inventory.values()) {
            p.display();
        }
    }
}
