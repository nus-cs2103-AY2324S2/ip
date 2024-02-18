package cleo;

/**
 * A custom exception class used within the Cleo/Duke application.
 */
public class DukeException extends Exception{

    /**
     * Creates a DukeException with a specific error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
    /**
     * Returns a formatted string representationrentation of the error message, suitable for
     * displaying to the user.
     *
     * @return The formatted error message
     */
    @Override
    public String toString() {
        return "     " + super.getMessage() + "\n";
    }
}
