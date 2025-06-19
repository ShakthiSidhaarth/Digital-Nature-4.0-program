import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Price: Rs " + price);
        System.out.println("----------------------------");
    }
}

public class ECommerceSearch {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();

        productList.add(new Product("Samsung Galaxy S24", "Mobile", 74999));
        productList.add(new Product("Apple iPhone 15", "Mobile", 89999));
        productList.add(new Product("Dell Inspiron Laptop", "Laptop", 55999));
        productList.add(new Product("Sony Headphones", "Electronics", 3999));
        productList.add(new Product("Nike Running Shoes", "Footwear", 2999));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter keyword to search products: ");
        String keyword = scanner.nextLine().toLowerCase();

        System.out.println("\nSearch Results:");
        boolean found = false;

        for (Product product : productList) {
            if (product.name.toLowerCase().contains(keyword) || 
                product.category.toLowerCase().contains(keyword)) {
                product.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No products found matching your search.");
        }

        scanner.close();
    }
}
