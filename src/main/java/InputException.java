public class InputException extends Exception {
    InputException(String message) {
        super(message);
    }

    InputException(String message, Throwable cause) {
        super(message, cause);
    }
}