package duke.exceptions;


public class MissingTaskToUnmarkException extends RuntimeException {

    public MissingTaskToUnmarkException(String errorMessage) {
        super(errorMessage);
    }
}

