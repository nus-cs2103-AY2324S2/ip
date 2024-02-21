package luke.exception;

/**
 * Thrown when the first word in the user input is not one of the recognised commands.
 */
public class UnknownCommandException extends Throwable {
    @Override
    public String toString() {
        return "\nSorry, please type a valid command. The valid commands are:\n"
                + "bye\n"
                + "list\n"
                + "mark\n"
                + "unmark\n"
                + "todo\n"
                + "deadline\n"
                + "event\n"
                + "delete\n"
                + "find\n";
    }
}
