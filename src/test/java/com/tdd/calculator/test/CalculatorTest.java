package com.tdd.calculator.test;

import org.junit.Test;
import com.tdd.calculator.Calculator;

import java.util.zip.DataFormatException;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testForEmptyString() throws DataFormatException {
        assertEquals(0, Calculator.add(""));
    }

    @Test
    public void testForOneNumber() throws DataFormatException {
        assertEquals(1, Calculator.add("1"));
    }

    @Test
    public void testForTwoNumbers() throws DataFormatException {
        assertEquals(7, Calculator.add("3,4"));
    }

    @Test
    public void testForSumOfMultipleNumbers() throws DataFormatException {
        assertEquals(21, Calculator.add("1,2,3,4,5,6"));
    }

    @Test
    public void testForSumOfMultipleValues() {
        try {
            Calculator.add("1,2,a,b,3,c,d,e");
        } catch (DataFormatException ife) {
            assertEquals("Invalid values in the input number string 1,2,a,b,3,c,d,e", ife.getMessage());
        }
    }

    @Test
    public void testForInputWithLineSeparators() throws DataFormatException {
        assertEquals(6, Calculator.add("1\n2,3"));
        try {
            Calculator.add("1,\n");
        } catch (DataFormatException ife) {
            assertEquals("Invalid values in the input number string 1,\n", ife.getMessage());
        }
        try {
            Calculator.add("1\n2,,3");
        } catch (DataFormatException ife) {
            assertEquals("Invalid values in the input number string 1\n2,,3", ife.getMessage());
        }
    }

    @Test
    public void testForCustomDelimiters() throws DataFormatException {
        assertEquals(6, Calculator.add("//%%\n1\n2%%3"));
    }
}