package duke.exceptions;

/**
 * Customized exception class for Duke project
 */
public class DukeException extends Exception {
    // Ex: Invalid commands
    /**
     * Constructor for DukeException
     *
     * @param str Error message that is thrown
     */
    public DukeException (String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}
