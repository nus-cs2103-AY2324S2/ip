package huyang;

/**
 * Custom exception class for handling task-related exceptions.
 */
public class TaskException extends Exception {
    /**
     * Constructor for the TaskException class.
     *
     * @param message The error message associated with the exception.
     */
    public TaskException(String message) {
        super(message);
    }

    /**
     * Creates a TaskException for an empty task description.
     *
     * @return A TaskException indicating that a task description is required.
     */
    public static TaskException forEmptyTaskDescription() {
        return new TaskException("A task description is required. Please try adding a task with a description.");
    }

    /**
     * Creates a TaskException for an invalid task format.
     *
     * @param taskType The type of the task for which the format is incorrect.
     * @return A TaskException indicating that the format for the task is incorrect.
     */
    public static TaskException forInvalidTaskFormat(String taskType) {
        return new TaskException("The format for the " + taskType + " task is incorrect. Please check and try again.");
    }

    /**
     * Creates a TaskException for an unknown command.
     *
     * @return A TaskException indicating that the command is invalid.
     */
    public static TaskException forUnknownCommand() {
        return new TaskException("Sorry, invalid command. Please try again.");
    }

    /**
     * Creates a TaskException for an invalid task number.
     *
     * @return A TaskException indicating that the task number is invalid.
     */
    public static TaskException forInvalidTaskNumber() {
        return new TaskException("Invalid task number. Please check and try again.");
    }
}

