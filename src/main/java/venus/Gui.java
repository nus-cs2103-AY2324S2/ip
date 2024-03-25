package venus;

import java.util.ArrayList;

/**
 * This is a Ui class the create Ui for messages i/o of program.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class Gui {
    private static final String START =
                    "    Hello! I'm Venus.\n"
                    + "    What can I do for you?\n";
    private static final String END =
                    "    Bye. Please close the tab by yourself. Hope to see you again soon!\n";

    public static String formatResponse(String message) {
        return "     " + message + "\n";
    }

    /**
     * Return a message that says project marked.
     * @param task The task that is marked.
     * @return Message returned to GUI.
     */
    public static String markMessage(Task task) {
        return Gui.formatResponse("Nice! I've marked this task as done:\n"
                + "       "
                + task.toString());
    }

    /**
     * Returns a message that says project is unmarked.
     * @param task The task that is unmarked.
     * @return Message returned to GUI.
     */
    public static String unmarkMessage(Task task) {
        return Gui.formatResponse("OK, I've marked this task as not done yet:\n"
                + "       "
                + task.toString());
    }

    /**
     * Returns a message that says project is marked.
     * @param task The task added.
     * @param size The size of the task that is formatted.
     * @return Message returned to GUI.
     */
    public static String addTaskMessage(Task task, int size) {
        return Gui.formatResponse("Got it. I've added this task:\n"
                + "       "
                + task.toString()
                + "\n"
                + "     Now you have " + size + " tasks in the list.");
    }

    /**
     * Returns a message that says a project is removed.
     * @param task The task to that is removed.
     * @param size The size of the task list after the removal.
     * @return Message returned to GUI.
     */
    public static String deleteTaskMessage(Task task, int size) {
        return Gui.formatResponse("Noted. I've removed this task:\n"
                + "       "
                + task.toString()
                + "\n"
                + "     Now you have " + (size - 1) + " tasks in the list.");
    }

    /**
     * Returns all tasks in current project.
     * @param tasks all the tasks in the Tasklist.
     * @return All tasks in the TaskList.
     */
    public static String allTasksMessage(TaskList tasks) {
        int i = 1;
        String output = "     Here are the tasks in your list:\n";
        for (Task s : tasks.getTasks()) {
            output = output + ("     " + i + "." + s + "\n");
            i++;
        }
        return output;
    }

    public static String getStart() {
        return START;
    }

    public static String getEnd() {
        return END;
    }

    /**
     * Returns all the tasks in a list.
     * @param items the list itself.
     * @return item string to be displayed by GUI.
     */
    public static String findMessage(ArrayList<String> items) {
        int i = 1;
        String output = "     Here are the matching tasks in your list:\n";
        for (String s : items) {
            output = output + ("     " + i + "." + s + "\n");
            i++;
        }
        return output;
    }

    /**
     * Returns all the tasks in a list that is duplicated
     * @param duplicates the duplicate tasks in the list.
     * @return item string to be displayed by GUI.
     */
    public static String duplicateMessage(ArrayList<Task> duplicates) {
        int i = 1;
        String output = "     Here are the duplicated tasks in your list:\n";
        for (Task s : duplicates) {
            output = output + ("     " + i + "." + s + "\n");
            i++;
        }
        output = output + "     Please take note that these are the second occurrences and beyond\n";
        output = output + "     You can delete specific list items using the delete command";
        return output;
    }
}
