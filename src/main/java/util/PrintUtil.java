package util;
import java.util.List;

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

    /**
     * Prints a list of tasks.
     *
     * @param tasks The list of tasks to be printed.
     */
    public static void printList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        if (!tasks.isEmpty()) {
            sb.append("I found these tasks!\n    ");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(".").append(tasks.get(i).toString());
                if (i != tasks.size() - 1) {
                    sb.append("\n    ");
                }
            }
        } else {
            sb.append("You don't have any tasks which match that description... (◞‸◟；)");
        }
        print(sb.toString());
    }
}
