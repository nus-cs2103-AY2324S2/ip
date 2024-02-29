package command;

import common.DukeException;

/**
 * Represents a command to be executed by the program.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @throws DukeException If command cannot be executed.
     */
    public abstract void execute() throws DukeException;

    /**
     * Returns true if the command executed exits the program.
     *
     * @return True if program will exit.
     */
    public abstract boolean isExit();
}
