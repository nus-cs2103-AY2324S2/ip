package util;

import task.Task;

/**
 * The PrintUtil class provides utility methods for printing formatted output.
 */
public class PrintUtil {

    /**
     * Private constructor to prevent instantiation of the PrintUtil class.
     */
    public PrintUtil() {

    }

    /**
     * Prints a spacer line to separate sections of output.
     */
    private static void printSpacer() {
        System.out.println();
        indent();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"
                + " - - - -\n");
    }

    /**
     * Prints an indentation to format the output.
     */
    private static void indent() {
        System.out.print("    ");
    }

    /**
     * Prints an indentation for task-related output.
     */
    private static void taskIndent() {
        indent();
        System.out.print("  ");
    }

    /**
     * Prints a string with proper indentation and spacer lines.
     *
     * @param s the string to be printed
     */
    public static void print(String s) {
        System.out.println();
        printSpacer();
        indent();
        System.out.println(s.replaceAll("\n", "\n    "));
        printSpacer();
    }

    /**
     * Prints a task object with proper indentation and spacer lines.
     *
     * @param t the task object to be printed
     */
    public static void print(Task t) {
        taskIndent();
        System.out.println(t);
        printSpacer();
    }
}
