package dude;

/**
 * Dude storage exception, thrown when unable to create storage.
 */
public class DudeParseException extends RuntimeException {
    /**
     * Class constructor.
     */
    public DudeParseException(String errorMessage) {
        super("Could not parse command: " + errorMessage);
    }

}
