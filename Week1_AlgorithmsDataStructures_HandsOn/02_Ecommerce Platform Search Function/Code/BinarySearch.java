import java.util.Arrays;
import java.util.Comparator;

public class BinarySearch {
    public static void sortById(Product[] products) {
        Arrays.sort(products, Comparator.comparingInt(p -> p.productId));
    }

    public static Product search(Product[] products, int targetId) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (products[mid].productId == targetId) {
                return products[mid];
            } else if (products[mid].productId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}
