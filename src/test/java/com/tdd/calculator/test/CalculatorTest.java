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
        } catch (DataFormatException dfe) {
            assertEquals("Invalid values in the input number string 1,2,a,b,3,c,d,e", dfe.getMessage());
        }
    }

    @Test
    public void testForInputWithLineSeparators() throws DataFormatException {
        assertEquals(6, Calculator.add("1\n2,3"));
        try {
            Calculator.add("1,\n");
        } catch (DataFormatException dfe) {
            assertEquals("Invalid values in the input number string 1,\n", dfe.getMessage());
        }
        try {
            Calculator.add("1\n2,,3");
        } catch (DataFormatException dfe) {
            assertEquals("Invalid values in the input number string 1\n2,,3", dfe.getMessage());
        }
    }

    @Test
    public void testForCustomDelimiters() throws DataFormatException {
        assertEquals(6, Calculator.add("//%\n1\n2%3"));
        assertEquals(6, Calculator.add("//;\n1\n2;3"));
        assertEquals(6, Calculator.add("//-\n1\n2-3"));
        assertEquals(6, Calculator.add("//%%\n1\n2%%3"));
        try {
            Calculator.add("//%%\n1\n2%%");
        } catch (DataFormatException dfe) {
            assertEquals("Invalid values in the input number string //%%\n1\n2%%", dfe.getMessage());
        }
    }

    @Test
    public void testForNegativeInputValues() {
        try {
            Calculator.add("//%\n1\n-2%3%-4");
        } catch (DataFormatException dfe) {
            assertEquals("Negatives not allowed: [-2, -4]", dfe.getMessage());
        }
    }

    @Test
    public void testToIgnoreBigNumbers() throws DataFormatException {
        assertEquals(1006, Calculator.add("//%\n1\n2%3%1000"));
        assertEquals(6, Calculator.add("//%\n1\n2%3%1001"));
    }

    @Test
    public void testForMultiCharacterDelimiter() throws DataFormatException {
        assertEquals(10, Calculator.add("//%%%\n1%%%4\n2%%%3"));
        assertEquals(12, Calculator.add("//***\n1***2\n6***3"));
    }

    @Test
    public void testForMultipleDelimiters() throws DataFormatException {
        assertEquals(21, Calculator.add("//%,*\n1,4\n2*3\n5%6"));
    }
}