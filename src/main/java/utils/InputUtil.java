package utils;

public class InputUtil {
    public static int parseIndex(String input) {
        String[] parts = input.split("\\s+");

        if (parts.length >= 2) {
            return Integer.parseInt(parts[1]) - 1;
        }

        return -1;
    }

    public static String getCommandType(String input) {
        return input.split(" ", 2)[0];
    }
}
