package arc.exceptions.tasks;

import arc.exceptions.ArcException;

/**
 * Represents an exception that is thrown when an operation references a task number
 * that does not exist in the task list. This could occur when trying to mark a task as done,
 * delete a task, or access a task's details using a task number that is out of the valid range.
 * This class extends {@link ArcException} to provide a specific error message for this situation.
 */
public class MissingTaskException extends ArcException {
    private static final String ERROR_MESSAGE = "The specified task does not exist.";

    /**
     * Constructs a new MissingTaskException with a predefined error message.
     * The message indicates that the user has referenced a task number that does not correspond
     * to any task in the current task list, either because the number is too high, too low,
     * or the task list is empty.
     */
    public MissingTaskException() {
        super(ERROR_MESSAGE);
    }
}
