import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testForEmptyString() {
        Calculator calculator = new Calculator();
        assertEquals(0, calculator.add(""));
    }
}