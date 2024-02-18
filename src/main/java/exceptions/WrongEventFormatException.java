package exceptions;

/**
 * Custom exception class representing the scenario where an input event format is incorrect.
 * Extends the DukeExceptions class.
 */
public class WrongEventFormatException extends DukeExceptions {

    /**
     * The message to be displayed when this exception is thrown.
     */
    private String msg = "Oops! This is given in an input that I cannot understand! \n"
            + "Your format should be event [name of task] /from [date] /to [date]";

    /**
     * Default constructor for the WrongEventFormatException class.
     */
    public WrongEventFormatException() {
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
