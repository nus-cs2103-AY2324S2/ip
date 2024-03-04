package irwyn.exceptions;

/**
 * This class represents a command exception for Irwyn chatbot.
 */
public class CommandException extends InputException {
    /**
     * Constructor for the CommandException class.
     */
    public CommandException() {
        super(
                "____________________________________________________________\n"
                + "Sorry, Invalid Command\n"
                + "use /by (date/time) for deadlines or use /from (date/time) and /to (date/time) for events\n"
                + "Please try again!\n"
                + "____________________________________________________________\n"
        );
    }
}
