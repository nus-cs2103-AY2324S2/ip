package exceptions;

/**
 * Custom exception class representing the scenario where an input deadline format is incorrect.
 * Extends the DukeExceptions class.
 */
public class WrongDeadlineFormatException extends DukeExceptions {

    /**
     * The message to be displayed when this exception is thrown.
     */
    private String msg = "Oops! This is given in an input that I cannot understand! \n"
            + "Your format should be deadline [name of task] /by [deadline]";

    public WrongDeadlineFormatException() {
        super();
    }

    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }
}
