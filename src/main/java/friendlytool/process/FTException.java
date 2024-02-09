package friendlytool.process;

/**
 * Class for handling exceptions
 */
public class FTException extends Exception {
    /**
     * Prints out the message
     *
     * @param message
     */
    public FTException(String message) {
        super(message);
    }
}
