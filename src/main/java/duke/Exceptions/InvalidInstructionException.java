package duke.Exceptions;

public class InvalidInstructionException extends RuntimeException {

    public InvalidInstructionException(String errorMessage) {
        super(errorMessage);
    }
}