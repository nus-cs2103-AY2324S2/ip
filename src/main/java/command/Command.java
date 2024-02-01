package command;

import objects.DukeException;

/**
 * Abstract class used to represent a command after the program parses it.
 */
public abstract class Command {

    /**
     * Method to allow the program to handle a command.
     */
    public abstract void handle() throws DukeException;

    /**
     * Method to indicate if a command is the 'bye' command.
     *
     * @return False if a command is not the 'bye' command, True if it is.
     */
    public abstract boolean isBye();
}
