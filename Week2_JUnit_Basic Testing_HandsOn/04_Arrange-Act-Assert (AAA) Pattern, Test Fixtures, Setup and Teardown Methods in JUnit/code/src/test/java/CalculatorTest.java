import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
        System.out.println("Setup: New Calculator created");
    }

    @AfterEach
    public void tearDown() {
        calculator = null;
        System.out.println("Teardown: Calculator cleared");
    }

    @Test
    public void testAdd() {
        int a = 5;
        int b = 3;

        int result = calculator.add(a, b);

        assertEquals(8, result, "Addition should return 8");
    }

    @Test
    public void testSubtract() {
        int a = 10;
        int b = 4;

        int result = calculator.subtract(a, b);

        assertEquals(6, result, "Subtraction should return 6");
    }
}
