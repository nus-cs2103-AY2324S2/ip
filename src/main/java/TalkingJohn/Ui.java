package talkingjohn;

/**
 * Represents the user interface of the TalkingJohn application.
 */
public class Ui {
     /**
     * Returns a goodbye message when the application exits.
     */
    public String goodbye() {
        return "Bye, hope to see you soon!\n" +
                "Tasks have been saved :)";
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

    /**
     * Generates a message indicating that the given task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A string containing a message indicating that the task has been marked as done.
     * @throws IllegalArgumentException if the task is null.
     */
    public String markTask(Task task) {
        assert task != null : "task cannot be null";
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Generates a message indicating that a task has been marked as not done.
     *
     * @param task The task to be marked as not done.
     * @return A string containing a message indicating that the task has been marked as not done.
     * @throws AssertionError if the task is null.
     */
    public String unmarkTask(Task task) {
        assert task != null : "task cannot be null";
        return "OK, I've marked this task as not done:\n" + task;
    }

    /**
     * Generates a message indicating that the input format is invalid.
     *
     * @return A string containing a message indicating that the input format is invalid.
     */
    public String invalidFormat() {
        return "OH NO! It seems like the format is wrong.";
    }

    /**
     * Returns an error message for invalid user input.
     */
    public String invalidInput() {
        return "HMM? This is an activity planner. Please repeat!";
    }

    /**
     * Generates an error message for invalid input expressions.
     *
     * @param input The invalid input provided by the user.
     * @return A string containing an error message for the invalid input expression.
     * @throws AssertionError if the input is null.
     */
    public String invalidInput(String input) {
        assert input != null : "user input cannot be null";
        return "OOPS!!! Invalid " + input + " expression.";
    }

    /**
     * Generates a success message after successfully deleting a task from the task list.
     *
     * @param task The task that has been successfully deleted.
     * @param size The number of tasks remaining in the list after deletion.
     * @return A string containing a success message indicating the deletion of the task and the updated size of the task list.
     * @throws AssertionError if the task is null.
     */
    public String deleteSuccess(Task task, int size) {
        assert task != null : "task cannot be null";
        return "Noted. I've removed this task:\n" + task +
                "\nNow you have " + size + " tasks in the list.";
    }
}
