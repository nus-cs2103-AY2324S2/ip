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
    public static String print(String msg) {
        System.out.println(msg);
        return msg + "\n";
    }

    /**
     * Prints the content received and a DIVIDER.
     *
     * @param msg Message to be printed.
     */
    public static String printWithDivider(String msg) {
        return print(msg);
    }

    /**
     * Prints an ArrayList of Tasks in a numbered format, with each entry on a new line.
     *
     * @param tasks ArrayList of Tasks that is to be printed.
     */
    public static String printNumberedDivider(ArrayList<Task> tasks) {
        String msg = "";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            msg += print(index + ". " + tasks.get(i));
        }
        return msg;
    }

    /**
     * Prints the content of an Exception.
     *
     * @param e Exception to be printed
     */
    public static String printException(Exception e) {
        return printWithDivider(e.getMessage());
    }
}
