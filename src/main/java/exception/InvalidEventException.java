package exception;

public class InvalidEventException extends NarutoException {
    InvalidEventException(String message) {
        super(message);
    }

    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.INVALID_EVENT;
    }
    
}
