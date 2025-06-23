public class InventoryTest {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        Product p1 = new Product("P101", "Mouse", 100, 499.99);
        Product p2 = new Product("P102", "Keyboard", 50, 999.99);
        Product p3 = new Product("P103", "Monitor", 20, 7999.00);

        manager.addProduct(p1);
        manager.addProduct(p2);
        manager.addProduct(p3);

        manager.displayAll();

        manager.updateProduct("P102", "Wireless Keyboard", 40, 1099.99);
        manager.deleteProduct("P103");

        manager.displayAll();
    }
}
