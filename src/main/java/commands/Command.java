package commands;

import core.Ui;
import data.Storage;
import tasks.TaskList;

/**
 * The Command class represents an abstract command that can be executed within the application.
 * Subclasses of this class define specific commands with their own implementations.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The task list on which the command operates.
     * @param ui      The user interface to display messages and interact with the user.
     * @param storage The storage to save or load data.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an ExitCommand, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
