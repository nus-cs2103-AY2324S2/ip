/**
 * Contains information on the DukeException class.
 *
 * @author Tee Chu Jie
 */
public class DukeException extends Exception {

    /**
     * The constructor for a DukeException object.
     *
     * @param message Handled by the super constructor.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Checks if a command is valid for the Chat Bot.
     * @param command The command to be checked.
     * @throws DukeException The exception that will be thrown if command is invalid.
     */
    public static void validate(String command) throws DukeException {
        if (!Duke.commands.contains(command)) {
            throw new DukeException("Invalid Command Given");
        }
    }

}
