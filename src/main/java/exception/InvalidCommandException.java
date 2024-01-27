package exception;

public class InvalidCommandException extends NarutoException {
    
    InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.INVALID_COMMAND;
    }
    
}
