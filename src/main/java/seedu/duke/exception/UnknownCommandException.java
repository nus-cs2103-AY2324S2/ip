package seedu.duke.exception;

/**
 * Represents an exception to be thrown when user key in command that is not defined
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructor of UnknownCommandException
     */
    public UnknownCommandException() {
        super("Bro, it seems that you have entered the wrong duke.command. "
                + "You can enter one of the following commands:\n"
                + "1. list\n"
                + "2. mark\n"
                + "3. unmark\n"
                + "4. delete\n"
                + "5. todo\n"
                + "6. event\n"
                + "7. deadline\n"
                + "8. check\n"
                + "9. find");

    }
}
