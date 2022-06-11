package com.tdd.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class Calculator {

    private Calculator() {}

    public static int add(String input) throws DataFormatException {
        if (isBlank(input)) {
            return 0;
        }

        String[] inputLines = input.split("\n"); // split the input string into lines
        String delimiter = ","; // default delimiter
        int start = 0;
        // get custom delimiter
        if (inputLines[0].startsWith("//")) {
            delimiter = getDelimiterRegex(inputLines[0]);
            start++; // start iterating from second line if the custom delimiter is present
        }

        int sum = 0;
        List<Integer> negativeNumbers = new ArrayList<>();
        for (int i = start; i < inputLines.length; i++) {
            validateInputLine(inputLines[i], input, delimiter);
            sum = processInputLine(input, delimiter, sum, inputLines[i], negativeNumbers);
        }

        if (!negativeNumbers.isEmpty()) {
            throw new DataFormatException(String.format("Negatives not allowed: %s", negativeNumbers));
        }
        return sum;
    }

    private static String getDelimiterRegex(String inputLine) {
        String delimiterString = inputLine.substring(2);
        StringBuilder delimiterSb = new StringBuilder(delimiterString.substring(0,1));
        for (int i = 1; i < delimiterString.length(); i++) {
            if (delimiterString.charAt(i) != delimiterString.charAt(i - 1)) {
                delimiterSb.append('|');
            }
            delimiterSb.append(delimiterString.charAt(i));
        }
        return delimiterSb.toString().replaceAll("\\*", "\\\\*");
    }

    private static void validateInputLine(String inputLine, String input, String delimiter) throws DataFormatException {
        // if an input line starts or ends with delimiter characters, it's an invalid input
        if (inputLine.startsWith(delimiter) || inputLine.endsWith(delimiter)) {
            throw new DataFormatException(String.format("Invalid values in the input number string %s", input));
        }
    }

    private static int processInputLine(String input, String delimiter, int sum, String inputLine, List<Integer> negativeNumbers) throws DataFormatException {
        try {
            int[] numbers = Arrays.stream(inputLine.split(delimiter)).mapToInt(Integer::parseInt).toArray();
            for (int number : numbers) {
                if (number > 1000) {
                    continue;
                }
                if (number < 0) {
                    negativeNumbers.add(number);
                }
                sum += number;
            }
        } catch (NumberFormatException nfe) {
            throw new DataFormatException(String.format("Invalid values in the input number string %s", input));
        }
        return sum;
    }
}