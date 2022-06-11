import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testForEmptyString() {
        assertEquals(0, Calculator.add(""));
    }

    @Test
    public void testForOneNumber() {
        assertEquals(1, Calculator.add("1"));
    }

    @Test
    public void testForTwoNumbers() {
        assertEquals(7, Calculator.add("3,4"));
    }
}