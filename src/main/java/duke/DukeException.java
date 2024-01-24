package duke;

/**
 * The {@code DukeException} class represents a custom exception specific to the Duke application.
 * It extends the standard {@code Exception} class and provides a constructor to initialize the
 * exception message in a stylized format.
 */
public class DukeException extends Exception {
    /**
     * Constructs a {@code DukeException} with a formatted exception message.
     *
     * @param exception The specific error message associated with the exception.
     */
    public DukeException(String exception) {
        super(
            "____________________________________________________________\n"
            + "   OOPS!!! " + exception + "\n"
            + "____________________________________________________________\n"
        );
    }
}
