package exception;

public class EmptyEventException extends NarutoException {
    
    EmptyEventException(String message) {
        super(message);
    }

    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.EMPTY_EVENT;
    }
}
