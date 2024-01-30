package nollid.exceptions;

/**
 * InvalidCommandException class represents an exception for invalid commands.
 * It extends the Exception class and includes a constructor to set the error message.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
