package duke.exceptions;

public class InvalidInstructionException extends RuntimeException {

    public InvalidInstructionException(String errorMessage) {
        super(errorMessage);
    }
}