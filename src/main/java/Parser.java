import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
                try {
                    for (int i = 0; i < dates.length; ++i) {
                        dates[i] = dates[i].trim();
                        dates[i] = LocalDate.parse(dates[i], Parser.DATE_TIME_FORMATTER).toString();
                    }
                    return new String[]{task.trim(), dates[0], dates[1]};
                } catch (DateTimeParseException e) {
                    return null;
                }
            }
        } else {
            if (!input.contains("/by ")) {
                return null;
            } else {
                final String[] res = input.split("/by ");
                if (!Parser.datePattern.matcher(res[1]).matches()) {
                    return null;
                }
                res[0] = res[0].trim();
                res[1] = res[1].trim();
                return res;
            }
        }
    }
    public static String getDate(String input) {
        String[] inputArr = input.split(" ");
        if (inputArr.length != 2) {
            return null;
        } else {
            try {
                return LocalDate.parse(inputArr[1], Parser.DATE_TIME_FORMATTER).toString();
            } catch (DateTimeParseException e) {
                return null;
            }
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
