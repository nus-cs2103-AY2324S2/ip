package shodan;

/**
 * An exception raised while running Shodan.
 */
public class ShodanException extends Exception {
    /**
     * Instantiates a new Shodan exception.
     *
     * @param message the message
     */
    public ShodanException(String message) {
        super(message);
    }
}
