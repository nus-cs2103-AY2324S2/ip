package exception;

public class InvalidActionException extends NarutoException{
    InvalidActionException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.INVALID_ACTION;
    }
}
