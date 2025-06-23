public class ProxyPatternTest {
    public static void main(String[] args) {
        Image img1 = new ProxyImage("nature.jpg");
        Image img2 = new ProxyImage("forest.jpg");
        Image img3 = new ProxyImage("nature.jpg"); 

        System.out.println("\n--- First display of 'nature.jpg' ---");
        img1.display();

        System.out.println("\n--- Displaying 'forest.jpg' ---");
        img2.display();

        System.out.println("\n--- Displaying 'nature.jpg' again ---");
        img3.display();
    }
}
