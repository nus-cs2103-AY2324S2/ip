package mychats.exception;

/**
 * Represents exceptions specific to mychats and inherits from the Exception class.
 */
public class MyChatsException extends Exception {

    /**
     * Constructs a MyChatsException with the given error message.
     *
     * @param message Error message for the exception.
     */
    public MyChatsException(String message) {
        super(message);
    }
}


