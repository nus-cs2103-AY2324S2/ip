package harper.exceptions;

/**
 * Exception that indicates an error is occurring during writing into file.
 */
public class HarperFileStoringException extends HarperException {
    public HarperFileStoringException() {
        super("Error occurs during storing!");
    }
}
