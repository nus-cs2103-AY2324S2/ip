package duke;

/**
 * The UI class handles user interface interactions.
 */
public class UI {
    private final String LINE = "______________________________________________________\n";

    /**
     * Constructs a new UI instance.
     */
    public UI() {}

    /**
     * Displays a welcome message when the program starts.
     */
    public void onStart() {
        System.out.print(LINE);
        System.out.println("Hello! I'm ChatterPal!");
        System.out.println("What can I do for you?");
        System.out.print(LINE);
    }

    /**
     * Displays a message when a task is added.
     *
     * @param s The task message to be displayed.
     */
    public void onTaskAddition(String s) {
        System.out.println(LINE + s + "\n" + LINE);
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param s      The deleted task message.
     * @param integer The number of tasks remaining.
     */
    public void onTaskDeletion(String s, int integer) {
        String message = String.format("%sNoted. I've removed this task:\n" +
                "%s\nNow you have %d tasks left.\n%s", LINE, s, integer, LINE);

        System.out.println(message);
    }

    /**
     * Displays a list of tasks.
     *
     * @param s The list of tasks to be displayed.
     */
    public void onPrintList(String s) {
        System.out.println(LINE + s + LINE);
    }


    /**
     * Displays a message when a task is marked as completed.
     *
     * @param s The completed task message.
     */
    public void onMark(String s) {
        String output = LINE + "Great job on completing the task!\n" + s + "\n" + LINE;
        System.out.println(output);
    }

    /**
     * Displays a message when a task is marked as incomplete.
     *
     * @param s The incomplete task message.
     */
    public void onUnmark(String s) {
        String output = LINE + "OK, I've marked this task as not done yet: \n" + s + "\n" + LINE;
        System.out.println(output);
    }

    /**
     * Displays a farewell message when the program ends.
     */
    public void onEnd() {
        System.out.println(LINE);
        System.out.println("Farewell! Can't wait to catch up with you again. Until next time, " +
                "take care and stay awesome! ");
        System.out.println(LINE);
    }

}
