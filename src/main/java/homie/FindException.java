package homie;

/**
 * FindException thrown when no keyword is given, or keyword is more than one word when using the find command.
 */
public class FindException extends Exception {
    /**
     * Constructor for MarkException class.
     *
     * @param message The error message.
     */
    public FindException(String message) {
        super("Bruh... " + message + "\nPlease follow the format:\nfind {KEYWORD}");
    }
}
