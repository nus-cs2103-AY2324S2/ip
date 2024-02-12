package aurora.command;

import aurora.objects.AuroraException;

/**
 * Abstract class used to represent a command after the program parses it.
 */
public class Command {

    /**
     * Method to allow the program to handle a command.
     */
    public void handle() throws AuroraException {
        System.out.println("This is a placeholder and should never be printed.");
    };

    /**
     * Method to indicate if a command is the 'bye' command.
     *
     * @return False if a command is not the 'bye' command, True if it is.
     */
    public boolean isBye() {
        return false;
    };

    /**
     * Method to allow the program to handle a command with GUI integration
     */
    public String handleGui() throws AuroraException {
        return "This is a placeholder and should never be returned.";
    }
}
