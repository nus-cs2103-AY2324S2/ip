package skyler.exception;

public class SkylerException extends Exception {

    public SkylerException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
