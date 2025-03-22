package exception;

/**
 * InvalidDateException is a specific type of DukeException that represents an error
 * when an invalid date format is encountered in the Duke application.
 * It provides a predefined error message indicating the expected date format.
 */
public class InvalidDateException extends DukeException {

    /**
     * Constructs an InvalidDateException with a predefined error message indicating
     * the expected date format.
     */
    public InvalidDateException() {
        super("Please use date format d/M/yyyy HHmm\neg 30/10/2024 1800");
    }
}
