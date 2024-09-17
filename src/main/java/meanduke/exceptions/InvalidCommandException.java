package meanduke.exceptions;

/**
 * Exception that is thrown when MeanDuke encounters an invalid user input.
 */
public class InvalidCommandException extends MeanDukeException {

    private final String errorMessage;

    /**
     * Constructs an InvalidCommandException class with the message being a general warning
     */
    public InvalidCommandException() {
        super();
        this.errorMessage = "What are you saying? Read the user manual, it was written for a reason";
    }

    /**
     * Constructs an InvalidCommandException class with the specified proper usage.
     *
     * @param usage Proper usage of the command that was given.
     */
    public InvalidCommandException(String usage) {
        super();
        this.errorMessage = usage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
