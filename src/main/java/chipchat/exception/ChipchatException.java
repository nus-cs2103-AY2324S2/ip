package chipchat.exception;

public abstract class ChipchatException extends RuntimeException {
    public ChipchatException(String msg) {
        super(msg);
    }
}
