import java.util.HashMap;
import java.util.Map;

public class ProxyImage implements Image {
    private String fileName;
    private static Map<String, RealImage> cache = new HashMap<>();

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        RealImage realImage = cache.get(fileName);

        if (realImage == null) {
            System.out.println("Image not in cache. Creating and loading...");
            realImage = new RealImage(fileName);
            cache.put(fileName, realImage);
        } else {
            System.out.println("Image found in cache.");
        }

        realImage.display();
    }
}
