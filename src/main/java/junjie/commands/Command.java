package junjie.commands;

import junjie.TaskList;
import junjie.Ui;

/**
 * Represents a command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @return The response from the command.
     */
    public abstract String execute(TaskList tasks, Ui ui);

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
