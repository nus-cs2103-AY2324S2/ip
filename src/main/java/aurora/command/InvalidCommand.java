package aurora.command;

import aurora.objects.AuroraException;

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
    public String handle() throws AuroraException {
        throw new AuroraException("I am unable to understand this command, please kindly try again.");
    }

    @Override
    public boolean isBye() {
        return false;
    }

}
