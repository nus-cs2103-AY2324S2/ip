package venus;

import java.util.ArrayList;

/**
 * This is a Ui class the create Ui for messages i/o of program.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class Ui {
    private static final String START =
            "    ____________________________________________________________\n"
                    + "    Hello! I'm Venus\n"
                    + "    What can I do for you?\n"
                    + "    ____________________________________________________________\n";
    private static final String END =
            "    ____________________________________________________________\n"
                    + "    Bye. Hope to see you again soon!\n"
                    + "    ____________________________________________________________\n";
    private static final String BORDER = "    ____________________________________________________________\n";

    public static void formatResponse(String message) {
        System.out.println(BORDER + "     " + message + "\n" + BORDER);
    }

    public static void formatMark(Task task) {
        Ui.formatResponse("Nice! I've marked this task as done:\n"
                + "       "
                + task.toString());
    }

    public static void formatUnmark(Task task) {
        Ui.formatResponse("OK, I've marked this task as not done yet:\n"
                + "       "
                + task.toString());
    }

    public static void formatTask(Task task, int size) {
        Ui.formatResponse("Got it. I've added this task:\n"
                + "       "
                + task.toString()
                + "\n"
                + "     Now you have " + size + " tasks in the list.");
    }

    public static void formatDelete(Task task, int size) {
        Ui.formatResponse("Noted. I've removed this task:\n"
                + "       "
                + task.toString()
                + "\n"
                + "     Now you have " + (size - 1) + " tasks in the list.");
    }

    public static void printList(TaskList tasks) {
        int i = 1;
        System.out.println(BORDER
                + "     Here are the tasks in your list:");
        for (Task s : tasks.getTasks()) {
            System.out.println("     " + i + "." + s);
            i++;
        }
        System.out.println(BORDER);
    }

    public static String getStart() {
        return START;
    }

    public static String getEnd() {
        return END;
    }

    public static void printFind(ArrayList<String> items) {
        int i = 1;
        System.out.println(BORDER
                + "     Here are the matching tasks in your list:");
        for (String s : items) {
            System.out.println("     " + i + "." + s);
            i++;
        }
        System.out.println(BORDER);
    }
}
