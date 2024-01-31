package duke.exceptions;

/**
 * Base exception for this project.
 */
public class BaseException extends RuntimeException{

    /**
     * Initializes BaseException based on the parent class RuntimeException.
     */
    public BaseException() {
        super();
    }

    /**
     * Initializes BaseException based on the parent class RuntimeException and message and cause given.
     * @param message warning message.
     * @param cause causation of the exception.
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Initializes BaseException based on the parent class RuntimeException and message given.
     * @param message warning message.
     */
    public BaseException(String message){
        super(message);
    }
}
