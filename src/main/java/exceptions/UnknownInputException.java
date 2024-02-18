package exceptions;

/**
 * Custom exception class representing the scenario where an unknown input is provided.
 * Extends the DukeExceptions class.
 */
public class UnknownInputException extends DukeExceptions {

    private String msg = "WHAT DO YOU MEANNNNN";

    public UnknownInputException() {
        super();
    }

    @Override
    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }
}
