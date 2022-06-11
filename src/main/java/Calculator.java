import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class Calculator {

    public static int add(String numbers) {
        if (isBlank(numbers)) {
            return 0;
        }
        return Arrays.stream(numbers.split(",")).mapToInt(Integer::parseInt).sum();
    }
}