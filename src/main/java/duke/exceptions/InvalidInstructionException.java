package duke.exceptions;

/**
 * Exception class that will be thrown when an invalid command is inputted by user.
 */
public class InvalidInstructionException extends RuntimeException {

    public InvalidInstructionException(String errorMessage) {
        super(errorMessage);
    }
}