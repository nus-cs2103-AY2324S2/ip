package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;

/**
 * Abstract class for commands that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks TaskList to be modified by the command.
     * @param file StateFile to be modified by the command.
     * @return String representation of the command's output.
     */
    public abstract String execute(TaskList tasks, StateFile file);
}
