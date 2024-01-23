package exception;

public class InvalidCommandException extends NarutoException {
    
    InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public NarutoException.ErrorType getErrorType() {
        return NarutoException.ErrorType.INVALID_COMMAND;
    }
    
}
