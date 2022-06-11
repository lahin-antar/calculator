import static org.apache.commons.lang3.StringUtils.isBlank;

public class Calculator {

    public static int add(String numbers) {
        if (isBlank(numbers)) {
            return 0;
        }
        String nums[] = numbers.split(",");
        if (nums.length == 1) {
            return Integer.parseInt(nums[0]);
        } else {
            return Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]);
        }
    }
}