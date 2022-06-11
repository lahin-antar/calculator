package com.tdd.calculator;

import java.util.Arrays;
import java.util.zip.DataFormatException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class Calculator {

    private Calculator() {}

    public static int add(String numbers) throws DataFormatException {
        if (isBlank(numbers)) {
            return 0;
        }
        try {
            String[] inputLines = numbers.split("\n");
            int sum = 0;
            for (String inputLine : inputLines) {
                // if first or last character of an input line has a delimiter character, it's an invalid input
                if (',' == inputLine.charAt(0) || ',' == inputLine.charAt(inputLine.length() - 1)) {
                    throw new DataFormatException(String.format("Invalid values in the input number string %s", numbers));
                }
                sum += Arrays.stream(inputLine.split(",")).mapToInt(Integer::parseInt).sum();
            }
            return sum;
        } catch (NumberFormatException nfe) {
            throw new DataFormatException(String.format("Invalid values in the input number string %s", numbers));
        }
    }
}