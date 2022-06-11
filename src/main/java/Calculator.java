import com.sun.media.sound.InvalidFormatException;

import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class Calculator {

    public static int add(String numbers) throws InvalidFormatException {
        if (isBlank(numbers)) {
            return 0;
        }
        try {
            return Arrays.stream(numbers.split(",")).mapToInt(Integer::parseInt).sum();
        } catch (NumberFormatException nfe) {
            throw new InvalidFormatException("Invalid values in the input number string");
        }
    }
}