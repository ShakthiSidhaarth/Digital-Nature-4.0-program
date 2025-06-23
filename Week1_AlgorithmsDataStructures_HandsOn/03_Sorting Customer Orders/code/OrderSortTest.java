public class OrderSortTest {
    public static void main(String[] args) {
        Order[] orders = {
            new Order("O101", "Shakthi", 2599.0),
            new Order("O102", "Aarav", 999.0),
            new Order("O103", "Meena", 4599.0),
            new Order("O104", "Vijay", 1500.0)
        };

        System.out.println(" Original Orders:");
        displayOrders(orders);

        System.out.println("\n Sorted by Bubble Sort:");
        SortUtils.bubbleSort(orders);
        displayOrders(orders);

        orders = new Order[] {
            new Order("O101", "Shakthi", 2599.0),
            new Order("O102", "Aarav", 999.0),
            new Order("O103", "Meena", 4599.0),
            new Order("O104", "Vijay", 1500.0)
        };

        System.out.println("\n Sorted by Quick Sort:");
        SortUtils.quickSort(orders, 0, orders.length - 1);
        displayOrders(orders);
    }

    private static void displayOrders(Order[] orders) {
        for (Order o : orders) {
            o.display();
        }
    }
}
