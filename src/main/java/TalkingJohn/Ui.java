package talkingjohn;

/**
 * Represents the user interface of the TalkingJohn application.
 */
public class Ui {

    /**
     * Returns a greeting message when the application starts.
     */
    public String greeting() {
        return "Hello, I am TalkingJohn\nWhat can I do for you?\n";
    }

    /**
     * Returns a goodbye message when the application exits.
     */
    public String goodbye() {
        return "Bye, hope to see you soon";
    }

    /**
     * Returns an error message when the user input is empty.
     *
     * @param input The empty user input.
     */
    public String emptyInput(String input) {
        assert input != null : "user input cannot be null";
        return "OOPS!!! " + input + " cannot be empty.";
    }

    /**
     * Returns a message after adding a task to the task list.
     *
     * @param task        The task that was added.
     * @param taskArrSize The size of the task array after adding the task.
     */
    public String addTask(Task task, int taskArrSize) {
        assert task != null : "task cannot be null";
        return "Got it. I've added this task:\n" +
                task + "\nNow you have " + taskArrSize + " tasks in the list.";
    }

    public String markTask(Task task) {
        assert task != null : "task cannot be null";
        return "Nice! I've marked this task as done:\n" + task;
    }

    public String unmarkTask(Task task) {
        assert task != null : "task cannot be null";
        return "OK, I've marked this task as not done:\n" + task;
    }

    public String invalidFormat() {
        return "OH NO! It seems like the format is wrong.";
    }

    /**
     * Returns an error message for invalid user input.
     */
    public String invalidInput() {
        return "HMM? This is an activity planner. Please repeat \uD83E\uDD72";
    }

    public String invalidInput(String input) {
        assert input != null : "user input cannot be null";
        return "OOPS!!! Invalid " + input + " expression.";
    }

    public String deleteSuccess(Task task, int size) {
        assert task != null : "task cannot be null";
        return "Noted. I've removed this task:\n" + task +
                "\nNow you have " + size + " tasks in the list.";
    }
}
