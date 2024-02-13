package irwyn.exceptions;

/**
 * This class represents an exception for Irwyn chatbot.
 */
public class IrwynException extends Exception {
    /**
     * Constructor for the IrwynException class.
     *
     * @param msg The message that details the exception.
     */
    public IrwynException(String msg) {
        super(msg);
    }
}
