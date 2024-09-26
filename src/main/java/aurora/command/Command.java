package aurora.command;

import aurora.objects.AuroraException;

/** Abstract class used to represent a command. */
public class Command {

    private static final String PLACEHOLDER = "This is a placeholder and should never be returned.";

    /**
     * Returns false by default.
     *
     * @return If the command is a bye command.
     */
    public boolean isByeCommand() {
        return false;
    };

    /**
     * Returns a String alerting users of the results of attempted handling of the command.
     *
     * @return A String that gives visual confirmation to the user that the command has been handled.
     * @throws AuroraException If an error occurs while handling a command.
     */
    public String handle() throws AuroraException {
        return PLACEHOLDER;
    }
}
