package baron.utils;

/**
 * Handles most String-related functions, such as parsing of input and extraction of values from
 * user input. Helps with catching common format exceptions.
 */
public class StringUtils {
    public static final String SEPARATOR = " | ";
    public static String getCommand(String input) {
        return input.split(" ")[0];
    }
    /**
     * Gets the value of a specified command string.
     *
     * @param input    The entire string
     * @param command  Command string to start search from. Search index is offset by the command's length
     * @param stopWord Optional parameter to indicate to the function that anything beyond this stopword's start index
     *                 will not be the value of the command. Search index is offset by -1 to avoid collecting the
     *                 stop word too
     * @return
     */
    public static String getValueOfCommand(String input, String command, String stopWord) {
        int commandIndex = getIndexOf(input, command, 0);
        // Validates against command that is at the end of the string
        if (commandIndex + command.length() >= input.length()) {
            throw new IllegalArgumentException("No valid value found after " + command);
        }
        commandIndex += command.length();
        String value = "";
        if (stopWord != null) {
            int endIndex = getIndexOf(input, stopWord, -1);
            value = input.substring(commandIndex, endIndex);
        } else {
            value = input.substring(commandIndex);
        }
        value = value.trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("No valid value found after " + command);
        }
        return value;
    }

    /**
     * Finds the index of a given string, and offset it by a given amount.
     *
     * @param input  The input to search
     * @param toFind The index of the string to find
     * @param offset The offset to move from the initial toFind String
     * @return The index of toFind in the position. Or throws an illegal argument exception if it doesn't exist
     */
    public static int getIndexOf(String input, String toFind, int offset) {
        int index = input.indexOf(toFind) + offset;
        if (index < 0 || index >= input.length()) {
            throw new IllegalArgumentException("No " + toFind + " specified");
        }
        return index;
    }

    /**
     * Abstracts out the splitting logic of the data string.
     *
     * @param data data to split by |
     * @return a array of the split up strings
     */
    public static String[] splitDataString(String data) {
        String[] segments = data.trim().split("\\s*\\|\\s*");
        return segments;
    }
}
