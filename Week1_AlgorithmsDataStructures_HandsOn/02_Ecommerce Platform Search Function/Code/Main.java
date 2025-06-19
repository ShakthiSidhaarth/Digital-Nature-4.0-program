public class Main {
    public static void main(String[] args) {
        Product[] products = {
            new Product(105, "Laptop", "Electronics"),
            new Product(101, "Shirt", "Clothing"),
            new Product(108, "Book", "Stationery"),
            new Product(103, "Mobile", "Electronics")
        };

        System.out.println("Linear Search:");
        Product found = LinearSearch.search(products, 108);
        System.out.println(found != null ? found : "Product not found");

        System.out.println("\nBinary Search:");
        BinarySearch.sortById(products);  
        found = BinarySearch.search(products, 108);
        System.out.println(found != null ? found : "Product not found");
    }
}
