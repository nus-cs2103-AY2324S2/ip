package floofy;

/**
 * Represents an exception that is thrown when the user inputs an invalid command when using Floofy chatbot.
 */
public class FloofyException extends Exception {
    /**
     * Constructs a new object of the FloofyException class.
     *
     * @param message The message to be displayed when the exception is thrown.
     */
    public FloofyException(String message) {
        super("You have input an invalid command! " + message);
    }
}
