package harper.exceptions;

/**
 * Exception that indicates an invalid command is entered.
 */
public class HarperInvalidCommandException extends HarperException {
    public HarperInvalidCommandException() {
        super("What do you mean? :-(");
    }
}
