package duke.exceptions;

public class MissingTaskToMarkException extends RuntimeException {

    public MissingTaskToMarkException(String errorMessage) {
        super(errorMessage);
    }
}
