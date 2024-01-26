import java.util.Arrays;

public class Parser {
    public static String getCommand(String input) {
        if (input.isEmpty()) {
            return null;
        } else {
            return input.split(" ")[0];
        }
    }
    public static String removeCommand(String input) {
        try {
            String[] inputArr = input.split(" ");
            return String.join(" ", Arrays.copyOfRange(inputArr, 1, inputArr.length));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public static String[] getTaskAndDates(String input, boolean isRange) {
        if (isRange) {
            if (!input.contains("/from ") || !input.contains("/to ")) {
                return null;
            } else {
                String[] inputArr = input.split("/from ");
                String task = inputArr[0];
                String[] dates = inputArr[1].split("/to ");
                return new String[]{task.trim(), dates[0].trim(), dates[1].trim()};
            }
        } else {
            if (!input.contains("/by ")) {
                return null;
            } else {
                return input.split("/by ");
            }
        }
    }
    public static String getDate(String input) {
        if (!input.contains("by")) {
            return null;
        } else {
            String[] inputArr = input.split(" ");
            return String.join(" ", Arrays.copyOfRange(inputArr, 1, inputArr.length));
        }
    }

    public static Integer getInteger(String input, int idx) {
        if (input.length() <= idx) {
            return null;
        } else {
            try {
                return Integer.parseInt(input.split(" ")[idx]);
            } catch (ArrayIndexOutOfBoundsException e) {
                final String output = String.format("Expecting input of at least %d", idx + 1);
                System.out.println(output);
                return null;
            } catch (NumberFormatException e) {
                System.out.println("Argument is not a valid number format!");
                return null;
            }
        }
    }
}
