import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(1)
    void testInitialize() {
        System.out.println("Test 1: Initialization");
    }

    @Test
    @Order(2)
    void testProcess() {
        System.out.println("Test 2: Processing");
    }

    @Test
    @Order(3)
    void testCleanup() {
        System.out.println("Test 3: Cleanup");
    }
}
