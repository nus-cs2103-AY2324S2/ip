package aurora.command;

import aurora.objects.DukeException;

/**
 * Abstract class used to represent a command after the program parses it.
 */
public class Command {

    /**
     * Method to allow the program to handle a command.
     */
    public void handle() throws DukeException {
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
}
