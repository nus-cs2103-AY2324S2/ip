package exceptions;

public class HarperException extends RuntimeException {
    public HarperException(String message) {
        super("\n" + message + "\n");
    }
}
