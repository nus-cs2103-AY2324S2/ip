package ping.exceptions;

/**
 * This class is used to create an exception for the Ping program
 */
public class PingException extends Exception {
    public PingException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage() + "\n";
    }
}
