package exceptions;

public class EmptyBodyException extends BaseException{
    public EmptyBodyException() {
        super("!!!ERROR: Please specify the content of the task you want to add.");
    }

    public EmptyBodyException(String message) {
        super(message);
    }
}
