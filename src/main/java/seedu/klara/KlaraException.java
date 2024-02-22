package seedu.klara;

/**
 * Represents <code>Exceptions</code> corresponding to
 * Klara commands from user input.
 */
public class KlaraException extends Exception {
    /**
     * Constructor for Klara-related exceptions.
     * @param e Represents the error message
     */
    public KlaraException(String e) {
        super(e);
    }
}
