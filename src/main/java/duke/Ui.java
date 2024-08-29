package duke;

/**
 * Ui is a utility class that provides methods for printing formatted
 * output to the terminal
 */
public class Ui {
    // Separator line to be used
    private static final String SEPARATOR_LINE = "____________________________________________________";

    /**
     * Wraps a string with separator lines and returns the result
     *
     * @param input The string to be wrapped
     * @return The input string wrapped with separator lines
     */
    public static String wrapWithSepLine(String input) {
        return SEPARATOR_LINE + "\n" + input + "\n" + SEPARATOR_LINE;
    }
}