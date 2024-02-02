package pingmebot;

public class PingMeException extends Exception {
    protected String message;
    public PingMeException(String message) {
        super(message);
        this.message = message;
    }
}
