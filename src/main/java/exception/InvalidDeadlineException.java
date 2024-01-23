package exception;

public class InvalidDeadlineException extends NarutoException {

    InvalidDeadlineException(String message) {
        super(message);
    }

    @Override
    public NarutoException.ErrorType getErrorType() {
        return NarutoException.ErrorType.INVALID_DEADLINE;
    }

}
