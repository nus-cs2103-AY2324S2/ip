package arc.exceptions.tasks;

import arc.exceptions.ArcException;

/**
 * Represents an exception that is thrown when a task is initialized with an empty description.
 * This class extends the {@link ArcException} to provide a specific error message
 * indicating that a task description cannot be empty.
 */
public class EmptyDescriptionException extends ArcException {
    private static final String ERROR_MESSAGE = "The description of a task cannot be empty.";

    /**
     * Constructs a new EmptyDescriptionException with a predefined error message.
     * The message indicates that a task description is required and cannot be left empty.
     */
    public EmptyDescriptionException() {
        super(ERROR_MESSAGE);
    }
}
