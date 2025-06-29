
import org.example.MathUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class MathUtilsTest {

    MathUtils utils = new MathUtils();

    @Test
    public void testSquare() {
        assertEquals(25, utils.square(5));
        assertEquals(0, utils.square(0));
        assertEquals(1, utils.square(1));
    }

    @Test
    public void testMax() {
        assertEquals(10, utils.max(10, 5));
        assertEquals(10, utils.max(5, 10));
        assertEquals(7, utils.max(7, 7)); // edge case
    }

    @Test
    public void testIsEven() {
        assertTrue(utils.isEven(2));
        assertTrue(utils.isEven(0));
        assertFalse(utils.isEven(3));
    }
}
