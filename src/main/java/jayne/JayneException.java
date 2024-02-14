package jayne;
/**
 * Custom exception class for handling specific exceptions related to Jayne application.
 * This class extends the standard Exception class and provides specific error messages
 * for various error scenarios encountered in the application.
 */
public class JayneException extends Exception {
    /**
     * Constructs a new JayneException with the specified detail message.
     *
     * @param message the detail message.
     */
    public JayneException(String message) {
        super(message);
    }
    /**
     * Constructs a JayneException for empty deadline time.
     *
     * @return a new JayneException with a message indicating that deadline time cannot be empty.
     */
    public static JayneException deadlineException() {
        return new JayneException("Where can have empty deadline time, enter "
                + "deadline lah. E.g. deadline return book /by yyyy-mm-dd");
    }
    /**
     * Constructs a JayneException for invalid unmark task number.
     *
     * @return a new JayneException with a message indicating that the task number for unmark is invalid.
     */
    public static JayneException unmarkException() {
        return new JayneException("??? Invalid task number. Enter a valid number please. E.g. unmark 1");
    }
    /**
     * Constructs a JayneException for unmarking a non-existent task.
     *
     * @param taskNumber the task number that does not exist.
     * @return a new JayneException with a message indicating that the task number does not exist for unmark.
     */
    public static JayneException unmarkTaskExistException(int taskNumber) {
        return new JayneException("Task number " + taskNumber + " does not exist. E.g. unmark 1");
    }
    /**
     * Constructs a JayneException for an empty task number when unmarking a task.
     *
     * @return a new JayneException with a message indicating that the task number for unmarking cannot be empty.
     */
    public static JayneException unmarkEmptyException() {
        return new JayneException("What are you doing? The number of the "
                + "task to unmark cannot be empty lah. E.g. unmark 1");
    }

    /**
     * Constructs a JayneException for an empty task number when deleting a task.
     *
     * @return a new JayneException with a message indicating that the task number for deletion cannot be empty.
     */
    public static JayneException deleteEmptyException() {
        return new JayneException("The task number to delete cannot be empty. E.g. delete 2");
    }
    /**
     * Constructs a JayneException for an invalid task number when deleting a task.
     *
     * @return a new JayneException with a message indicating that an invalid task number was provided for deletion.
     */
    public static JayneException deleteInvalidException() {
        return new JayneException("??? Invalid task number. Enter a valid number please. E.g. delete 2");
    }
    /**
     * Constructs a JayneException for a non-existent task number when deleting a task.
     *
     * @param taskNumber the task number that does not exist.
     * @return a new JayneException with a message indicating that the task number does not exist for deletion.
     */
    public static JayneException deleteExistException(int taskNumber) {
        return new JayneException("Task number " + taskNumber + " does not exist. E.g. delete 2");
    }

    /**
     * Constructs a JayneException for an empty description in a to-do task.
     *
     * @return a new JayneException with a message indicating that the description of a to-do task cannot be empty.
     */
    public static JayneException todoException() {
        return new JayneException("Huh?? why your description of a todo "
                + "is empty! Please enter description. E.g. todo read book");
    }

    /**
     * Constructs a JayneException for an empty description in an event task.
     *
     * @return a new JayneException with a message indicating that the description of an event task cannot be empty.
     */
    public static JayneException emptyEventException() {
        return new JayneException("The description of an event "
                + "cannot be empty lah. E.g. event project meeting /from Mon 2pm /to 4pm");
    }
    /**
     * Constructs a JayneException for an empty start time in an event task.
     *
     * @return a new JayneException with a message indicating that the start time of an event task cannot be empty.
     */
    public static JayneException eventStartException() {
        return new JayneException("The event start time cannot be empty. "
                + "E.g. event project meeting /from Mon 2pm /to 4pm");
    }
    /**
     * Constructs a JayneException for an empty end time in an event task.
     *
     * @return a new JayneException with a message indicating that the end time of an event task cannot be empty.
     */
    public static JayneException eventEndException() {
        return new JayneException("The event end time cannot be empty please. "
                + "E.g. event project meeting /from Mon 2pm /to 4pm");
    }
    /**
     * Constructs a JayneException for an empty task number when marking a task.
     *
     * @return a new JayneException with a message indicating that the task number for marking cannot be empty.
     */
    public static JayneException markEmptyException() {
        return new JayneException("The task number to mark cannot be empty. E.g. mark 1");
    }
    /**
     * Constructs a JayneException for a non-existent task number when marking a task.
     *
     * @param taskNumber the task number that does not exist.
     * @return a new JayneException with a message indicating that the task number does not exist for marking.
     */
    public static JayneException markTaskExistException(int taskNumber) {
        return new JayneException("Task number " + taskNumber + " does not exist. E.g. mark 1");
    }
    /**
     * Constructs a JayneException for an invalid task number when marking a task.
     *
     * @return a new JayneException with a message indicating that an invalid task number was provided for marking.
     */
    public static JayneException markInvalidTaskException() {
        return new JayneException("??? Invalid task number. Enter a valid number please. E.g. mark 1");
    }
}
