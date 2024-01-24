package Exceptions;


public class MissingTaskToUnmarkException extends RuntimeException {

    public MissingTaskToUnmarkException(String errorMessage) {
        super(errorMessage);
    }
}

