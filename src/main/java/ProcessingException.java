public class ProcessingException extends Exception {
    ProcessingException(String message) {
        super(message);
    }

    ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}