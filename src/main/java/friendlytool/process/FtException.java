package friendlytool.process;

/**
 * Class for handling exceptions
 */
public class FtException extends Exception {
    /**
     * Prints out the message
     *
     * @param message
     */
    public FtException(String message) {
        super(message);
    }
}
