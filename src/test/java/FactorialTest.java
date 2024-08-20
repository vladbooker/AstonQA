import org.example.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {

    @Test
    void testFactorialOfZero() {
        assertEquals(1, Main.calculateFactorial(0));
    }

    @Test
    void testFactorialOfFive() {
        assertEquals(120, Main.calculateFactorial(5));
    }

    @Test
    void testFactorialOfTen() {
        assertEquals(3628800, Main.calculateFactorial(10));
    }
}