package exception;

public class EmptyCommandException extends NarutoException {

    EmptyCommandException(String message) {
        super(message);
    }

    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.EMPTY_COMMAND;
    }
}
