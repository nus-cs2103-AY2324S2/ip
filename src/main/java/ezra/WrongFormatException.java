package ezra;

/**
 * Exception thrown when there is an incorrect format in user input commands.
 */
public class WrongFormatException extends Exception {

    /**
     * Constructs a WrongFormatException with the specified error message.
     *
     * @param message The error message describing the incorrect format.
     */
    public WrongFormatException(String message) {
        super(message);
    }
}
