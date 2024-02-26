package friendlytool.process;

/**
 * Class for handling exceptions
 */
public class FtException extends Exception {
    /**
     * Constructs FtException
     *
     * @param message message that needs to be displayed
     */
    public FtException(String message) {
        super(message);
    }
}
