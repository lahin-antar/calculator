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
        String[] inputLines = numbers.split("\n");
        String delimiter = ",";
        int start = 0;
        int sum = 0;
        if (inputLines[0].startsWith("//")) {
            delimiter = inputLines[0].substring(2);
            start++;
        }
        for (int i = start; i < inputLines.length; i++) {
            String inputLine = inputLines[i];
            // if an input line starts or ends with delimiter characters, it's an invalid input
            if (inputLine.startsWith(delimiter) || inputLine.endsWith(delimiter)) {
                throw new DataFormatException(String.format("Invalid values in the input number string %s", numbers));
            }
            try {
                sum += Arrays.stream(inputLine.split(delimiter)).mapToInt(Integer::parseInt).sum();
            } catch (NumberFormatException nfe) {
                throw new DataFormatException(String.format("Invalid values in the input number string %s", numbers));
            }
        }
        return sum;
    }
}