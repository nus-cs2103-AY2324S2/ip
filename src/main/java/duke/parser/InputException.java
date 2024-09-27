package duke.parser;

/**
 * Represents exception thrown when input is invalid.
 */
public abstract class InputException extends Exception {
    public InputException(String message) {
        super(message);
    }
}