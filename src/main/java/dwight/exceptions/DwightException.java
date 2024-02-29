package dwight.exceptions;

/**
 * Customized exception class for Duke project
 */
public class DwightException extends Exception {
    // Ex: Invalid commands
    /**
     * Constructor for DukeException
     *
     * @param str Error message that is thrown
     */
    public DwightException (String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}
