package badgpt.exceptions;

/**
 * Signals that the task could not be found in the task list.
 */
public class TaskNotFoundException extends TaskException {
    private int numTasks;

    /**
     * Creates a new TaskNotFoundException with the specified message and number of tasks.
     *
     * @param message The error message.
     * @param numTasks The current number of tasks in the task list.
     */
    public TaskNotFoundException(String message, int numTasks) {
        super(message);
        this.numTasks = numTasks;
    }

    /**
     * Returns a string representation of the exception.
     */
    @Override
    public String toString() {
        return "The requested task cannot be found. " + (numTasks == 0
                ? "There are no tasks."
                : "Please enter a number equal to or less than " + numTasks + ".");
    }
}
