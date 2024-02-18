package exceptions;

/**
 * Custom exception class representing the scenario where we delete/ mark an index is out of range.
 * Extends the DukeExceptions class.
 */
public class OutOfIndexException extends DukeExceptions {

    /**
     * The message to be displayed when O exception is thrown.
     */
    private String msg = "The index you indicated is out of range!";

    /**
     * Default constructor for the OutOfIndexException class.
     */
    public OutOfIndexException() {
        super();
    }

    /**
     * Method to output the formatted exception message.
     *
     * @return Formatted string containing the parent output and the custom message.
     */
    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }
}
