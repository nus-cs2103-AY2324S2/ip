public class PandaException extends Exception {
    public PandaException() {
        super();
    }

    public PandaException(String message) {
        super(message);
    }

    public PandaException(Throwable cause) {
        super(cause);
    }

    public PandaException(String message, Throwable cause) {
        super(message, cause);
    }
}
