package harper.exceptions;

/**
 * Exception that indicates an invalid index is entered.
 */
public class HarperInvalidIndexException extends HarperException {
    public HarperInvalidIndexException() {
        super("Please make sure u have entered a valid index!");
    }
}
