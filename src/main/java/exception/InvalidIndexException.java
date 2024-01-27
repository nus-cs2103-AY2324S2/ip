package exception;

public class InvalidIndexException extends NarutoException {
    InvalidIndexException(String message) {
        super(message);
    }

    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.INVALID_INDEX;
    }
}
