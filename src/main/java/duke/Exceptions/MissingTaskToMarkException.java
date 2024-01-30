package duke.Exceptions;

public class MissingTaskToMarkException extends RuntimeException {

    public MissingTaskToMarkException(String errorMessage) {
        super(errorMessage);
    }
}
