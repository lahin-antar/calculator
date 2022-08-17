package com.tdd.calculator.test;

import com.tdd.calculator.Calculator;
import org.junit.jupiter.api.Test;

import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;

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
        Exception exception = assertThrows(DataFormatException.class, () -> Calculator.add("1,2,a,b,3,c,d,e"));

        String expectedMessage = "Invalid values in the input number string 1,2,a,b,3,c,d,e";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testForInputWithLineSeparators() throws DataFormatException {
        assertEquals(6, Calculator.add("1\n2,3"));
        Exception exception = assertThrows(DataFormatException.class, () -> Calculator.add("1,\n"));

        String expectedMessage = "Invalid values in the input number string 1,\n";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        Exception exception2 = assertThrows(DataFormatException.class, () -> Calculator.add("1\n2,,3"));

        String expectedMessage2 = "Invalid values in the input number string 1\n2,,3";
        String actualMessage2 = exception2.getMessage();

        assertTrue(actualMessage2.contains(expectedMessage2));
    }

    @Test
    public void testForCustomDelimiters() throws DataFormatException {
        assertEquals(6, Calculator.add("//%\n1\n2%3"));
        assertEquals(6, Calculator.add("//;\n1\n2;3"));
        assertEquals(6, Calculator.add("//-\n1\n2-3"));
        assertEquals(6, Calculator.add("//%%\n1\n2%%3"));

        Exception exception = assertThrows(DataFormatException.class, () -> Calculator.add("//%%\n1\n2%%"));

        String expectedMessage = "Invalid values in the input number string //%%\n1\n2%%";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testForNegativeInputValues() {
        Exception exception = assertThrows(DataFormatException.class, () -> Calculator.add("//%\n1\n-2%3%-4"));

        String expectedMessage = "Negatives not allowed: [-2, -4]";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
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

    @Test
    public void testForMultipleMultiCharDelimiters() throws DataFormatException {
        assertEquals(21, Calculator.add("//%%%,,****\n1,,4\n2****3\n5%%%6"));
    }
}