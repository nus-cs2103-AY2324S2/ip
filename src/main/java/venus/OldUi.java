package venus;

import java.util.ArrayList;

/**
 * This is a Ui class the create Ui for messages i/o of program.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class OldUi {
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

    /**
     * Return a message that says project marked.
     * @param task The task that is marked.
     */
    public static void formatMark(Task task) {
        OldUi.formatResponse("Nice! I've marked this task as done:\n"
                + "       "
                + task.toString());
    }

    /**
     * Returns a message that says project is unmarked.
     * @param task The task that is unmarked.
     */
    public static void formatUnmark(Task task) {
        OldUi.formatResponse("OK, I've marked this task as not done yet:\n"
                + "       "
                + task.toString());
    }

    /**
     * Returns a message that says project is marked.
     * @param task The task added.
     * @param size The size of the task that is formatted.
     */
    public static void formatTask(Task task, int size) {
        OldUi.formatResponse("Got it. I've added this task:\n"
                + "       "
                + task.toString()
                + "\n"
                + "     Now you have " + size + " tasks in the list.");
    }

    /**
     * Returns a message that says a project is removed.
     * @param task The task to that is removed.
     * @param size The size of the task list after the removal.
     */
    public static void formatDelete(Task task, int size) {
        OldUi.formatResponse("Noted. I've removed this task:\n"
                + "       "
                + task.toString()
                + "\n"
                + "     Now you have " + (size - 1) + " tasks in the list.");
    }

    /**
     * Returns all tasks in current project.
     * @param tasks all the tasks in the Tasklist.
     */
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

    /**
     * Returns all the tasks in a list.
     * @param items the list itself
     */
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
