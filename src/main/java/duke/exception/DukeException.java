package duke.exception;

<<<<<<< Updated upstream
 public class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }

        public String toString() {
            return "Ummm, " + getMessage();
        }
}
=======
/**
 * DukeException is a custom exception class for handling exceptions specific to the Duke application.
 * It extends the standard Exception class and provides a customized toString method.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the DukeException, providing additional context.
     *
     * @return A string representation of the exception.
     */
    public String toString() {
        return "Ummm, " + getMessage();
    }
}
>>>>>>> Stashed changes
