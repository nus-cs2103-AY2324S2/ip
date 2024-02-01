package command;
import objects.DukeException;

/**
 * Class that handles an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Constructor for the InvalidCommand class.
     */
    public InvalidCommand() {

    }

    @Override
    public void handle() throws DukeException {
        throw new DukeException("I am unable to understand this command, please kindly try again.");
    }

    @Override
    public boolean isBye() {
        return false;
    }

}
