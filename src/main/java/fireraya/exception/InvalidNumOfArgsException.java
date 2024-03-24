package fireraya.exception;

/**
 * An extended class of the FirerayaException class.
 *
 * This class handles a special and commonly thrown exception for wrong number of arguements.
 */

public class InvalidNumOfArgsException extends FirerayaException {

    /**
     * Constructor to throw a InvalidNumOfArgs exception.
     */
    public InvalidNumOfArgsException() {
        super("Invalid number of arguments");
    }
}
