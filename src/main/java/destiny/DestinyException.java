package destiny;

/**
 * An extension of the Exception class used to identify exceptions unique to Destiny.
 */
public class DestinyException extends Exception {
    public DestinyException() {
        super();
    }

    public DestinyException(String message) {
        super("Something went wrong...\n" + message);
    }
}
