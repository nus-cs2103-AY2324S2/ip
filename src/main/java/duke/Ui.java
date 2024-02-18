package duke;

/**
 * The UI class handles user interface interactions.
 */
public class Ui {

    /**
     * Constructs a new UI instance.
     */
    public Ui() {}

    /**
     * Displays a welcome message when the program starts.
     */
    public String onStart() {
        return "Hello! I'm ChatterPal!" + "\n" + "What can I do for you?" + "\n";
    }

    /**
     * Displays a message when a task is added.
     *
     * @param s The task message to be displayed.
     */
    public String onTaskAddition(String s) {
        return s + "\n";
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param s      The deleted task message.
     * @param integer The number of tasks remaining.
     */
    public String onTaskDeletion(String s, int integer) {
        String message = String.format("Noted. I've removed this task:\n"
                + "%s\nNow you have %d tasks left.\n", s, integer);

        return message;
    }

    /**
     * Displays a list of tasks.
     *
     * @param s The list of tasks to be displayed.
     */
    public String onPrintList(String s) {
        return s;
    }

    /**
     * Prints the tasks matching a search query, surrounded by a separator line.
     *
     * @param s The string representing the matching tasks.
     */
    public String onPrintFind(String s) {
        return "Here are the matching tasks in your list:\n" + s;
    }
    /**
     * Displays a message when a task is marked as completed.
     *
     * @param s The completed task message.
     */
    public String onMark(String s) {
        String output = "Great job on completing the task!\n" + s + "\n";
        return output;
    }

    /**
     * Displays a message when a task is marked as incomplete.
     *
     * @param s The incomplete task message.
     */
    public String onUnmark(String s) {
        String output = "OK, I've marked this task as not done yet: \n" + s + "\n";
        return output;
    }

    /**
     * Displays a farewell message when the program ends.
     */
    public String onEnd() {
        return "\n" + "Farewell! Can't wait to catch up with you again. Until next time, "
                + "take care and stay awesome!\n";
    }

}
