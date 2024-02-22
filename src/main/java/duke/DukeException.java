package duke;

/**
 * Custom exception class for Duke chatbot.
 * Extends the RuntimeException class to handle Duke-specific exceptions.
 */
public class DukeException extends RuntimeException {

    /**
     * Exception message template for when creating a task with an empty description.
     */
    public static final String NON_EMPTY_DESC = "The description of a %s cannot be empty!";

    /**
     * Exception message template for an unknown command.
     */
    public static final String UNKNOWN_CMD = "I'm sorry, but I don't know what %s means :-(";

    /**
     * Constructs a DukeException with a formatted error message.
     *
     * @param message The specific error message for the exception.
     */
    public DukeException(String message) {
        super(String.format("   Hmm, %s", message));
    }
}
