package exceptions;

/**
 * The InvalidInputException class represents an exception that is thrown when a data format is
 * incompatible with the expected format.
 */
public class InvalidInputException extends LeluException{

    public InvalidInputException(String message) {
        super(message);
    }
}
