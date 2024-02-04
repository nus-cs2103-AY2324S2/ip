package exception;

/**
 * InvalidEventException is a specific type of DukeException that represents an error
 * when an invalid event format is encountered in the Duke application.
 * It provides a predefined error message indicating the expected event input format.
 */
public class InvalidEventException extends DukeException {

    /**
     * Constructs an InvalidEventException with a predefined error message indicating
     * the expected event input format.
     */
    public InvalidEventException() {
        super("Input in the form:\nevent Hackathon /from 8/10/2024 2000 /to 12/10/2024 1600");
    }
}

