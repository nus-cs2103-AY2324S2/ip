package homie;

/**
 * Find exception class. Handles all exceptions related to Find command.
 * Thrown when no keyword is given, or keyword is more than one word.
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
