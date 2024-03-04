package irwyn.exceptions;

/**
 * This class represents an input exception for Irwyn chatbot.
 */
public class InputException extends IrwynException {
    /**
     * Constructor for the InputException class.
     *
     * @param msg The message that details the input exception.
     */
    public InputException(String msg) {
        super(msg);
    }
}
