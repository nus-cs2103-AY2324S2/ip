package shuheng.exceptions;

/**
 * This class represents errors caused by invalid parameters.
 */
public class InvalidParametersException extends InvalidInputException {
    /**
     * Constructor for invalid parameters.
     */
    public InvalidParametersException() {
        super("That's an invalid parameter, "
            + "remember to mark your priority with /priority {P}, where P can be 1, 2 or 3! "
            + "Make sure to use /by to indicate time if you are creating a deadline, "
            + "or /from and /to if you are creating a event!");
    }
}
