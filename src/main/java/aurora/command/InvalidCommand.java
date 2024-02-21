package aurora.command;

import aurora.objects.AuroraException;

/** The InvalidCommand Class represents invalid commands. */
public class InvalidCommand extends Command {

    /** Constructs an InvalidCommand object. */
    public InvalidCommand() {

    }

    @Override
    public String handle() throws AuroraException {
        throw new AuroraException(AuroraException.INVALID_COMMAND);
    }

}
