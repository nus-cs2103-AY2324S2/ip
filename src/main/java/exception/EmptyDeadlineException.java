package exception;

public class EmptyDeadlineException extends NarutoException {

    EmptyDeadlineException(String message) {
        super(message);
    }

    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.EMPTY_DEADLINE;
    }
    
}
