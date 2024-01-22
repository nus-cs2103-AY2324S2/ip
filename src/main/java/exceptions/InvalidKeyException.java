package exceptions;

public class InvalidKeyException extends BaseException{
    public InvalidKeyException() {
        super("!!!ERROR: Sorry we can't recognize your instruction, please use valid one.");
    }

    public InvalidKeyException(String message) {
        super(message);
    }
}
