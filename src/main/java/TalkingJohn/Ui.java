package talkingjohn;

/**
 * Represents the user interface of the TalkingJohn application.
 */
public class Ui {

    /**
     * Displays a greeting message when the application starts.
     */
    public String greeting() {
        return "Hello, I am TalkingJohn\nWhat can I do for you?\n";
    }

    /**
     * Displays a goodbye message when the application exits.
     */
    public String goodbye() {
        return "Bye, hope to see you soon";
    }

    /**
     * Displays an error message when the user input is empty.
     *
     * @param input The empty user input.
     */
    public String emptyInput(String input) {
        return "OOPS!!! " + input + " cannot be empty.";
    }

    /**
     * Displays a message after adding a task to the task list.
     *
     * @param task        The task that was added.
     * @param taskArrSize The size of the task array after adding the task.
     */
    public String printAddTask(Task task, int taskArrSize) {
        return "Got it. I've added this task:\n" +
                task + "\nNow you have " + taskArrSize + " tasks in the list.";
    }

    /**
     * Displays an error message for invalid user input.
     */
    public String invalidInput() {
        return "HMM? This is an activity planner. Please repeat \uD83E\uDD72";
    }
}
