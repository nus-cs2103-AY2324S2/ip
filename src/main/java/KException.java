/**
 * Exception class for all errors or warning generated within the KBot program.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class KException extends Exception {

    /**
     * Constructor for KException.
     * @param message The String message to be printed when an error is thrown.
     */
    public KException(String message){
        super(message);
    }
}

/**
 * Exceptions to handle
 * 1. Invalid parameters for todo, deadline, event.
 * 2. Invalid command
 * 3. mark/unmark index out of range
 */