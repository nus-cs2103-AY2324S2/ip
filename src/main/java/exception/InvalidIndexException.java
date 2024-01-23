package exception;

public class InvalidIndexException extends NarutoException {
    InvalidIndexException(String message) {
        super(message);
    }

    @Override
    public NarutoException.ErrorType getErrorType() {
        return NarutoException.ErrorType.INVALID_INDEX;
    }
}
