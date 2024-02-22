package exceptions;

/**
 * Handles general exceptions such as invalid command words.
 */
public class ChrisPBaconException extends Exception {
    /**
     * Constructor for ChrisPBaconException with the error message.
     *
     * @param errorMessage to be printed on console.
     */
    public ChrisPBaconException(String errorMessage) {
        super(errorMessage);
    }
}
