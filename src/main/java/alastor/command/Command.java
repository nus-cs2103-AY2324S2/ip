package alastor.command;

import alastor.AlastorException;
import alastor.Storage;
import alastor.TaskList;
import alastor.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     * @throws AlastorException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws AlastorException;
}
