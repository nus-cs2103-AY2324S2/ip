package judy.exceptions;

/**
 * InvalidEventException is a specific type of Judy Exception that shows
 * an error when invalid event format is entered by users.
 */
public class InvalidEventException extends JudyException {

    /**
     * Constructs an InvalidEventException to show error message indicating
     * the expected event input format.
     */
    public InvalidEventException() {
        super("Invalid event format! Try this:\n "
                + "event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
    }
}
