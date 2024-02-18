package nihao.handler;

import java.util.ArrayList;

import nihao.action.task.Task;
import nihao.enums.SavedString;

/**
 * Handles all printing requests.
 */
public final class PrintHandler {
    private static final String DIVIDER = "------------------------------------";
    private PrintHandler() {}

    /**
     * Simply prints the content received.
     *
     * @param msg Message to be printed.
     */
    public static void print(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints the content received and a DIVIDER.
     *
     * @param msg Message to be printed.
     */
    public static void printWithDivider(String msg) {
        print(msg);
        print(DIVIDER);
    }

    /**
     * Prints an ArrayList of Tasks in a numbered format, with each entry on a new line.
     *
     * @param tasks ArrayList of Tasks that is to be printed.
     */
    public static void printNumberedDivider(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            print(index + ". " + tasks.get(i));
        }
        print(DIVIDER);
    }

    /**
     * Prints the content of an Exception.
     *
     * @param e Exception to be printed
     */
    public static void printException(Exception e) {
        printWithDivider(e.getMessage());
    }

    /**
     * Prints the app logo and the greeting line.
     */
    public static void printInit() {
        printWithDivider(SavedString.LOGO.getContent());
        printWithDivider(SavedString.GREETINGS.getContent());
    }
}
