import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CalculatorTest {
    Calculator calc = new Calculator();
    @Test
    public void testAddition() {
        assertEquals(10, calc.add(6, 4));
        assertNotEquals(11, calc.add(6, 4));
    }
    @Test
    public void testSubtraction() {
        assertEquals(2, calc.subtract(6, 4));
        assertNotEquals(3, calc.subtract(6, 4));
    }
    @Test
    public void testMultiplication() {
        assertEquals(24, calc.multiply(6, 4));
        assertNotEquals(25, calc.multiply(6, 4));
    }
    @Test
    public void testDivision() {
        assertEquals(2, calc.divide(8, 4));
        assertNotEquals(3, calc.divide(8, 4));
    }
    @Test
    public void testDivisionByZeroThrowsException() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }
}
