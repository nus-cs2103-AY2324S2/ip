package duke.exceptions;

/**
 * This class is the parent class for all Exceptions specific to the Duke Bot.
 *
 * @author delishad21
 */
public class DukeException extends Exception{
    /**
     * Creates a DukeException, thrown when an error specific to the dukebot happens.
     *
     * @param errorMsg error message to print
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }

}
