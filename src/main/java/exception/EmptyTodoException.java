package exception;

public class EmptyTodoException extends NarutoException {

    EmptyTodoException(String message) {
        super(message);
    }

    @Override
    public NarutoException.ErrorType getErrorType() {
        return NarutoException.ErrorType.EMPTY_TODO;
    }
    

}
