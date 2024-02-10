package commands;

import util.Ui;
import util.TaskList;
import util.Storage;

/**
 * The Command abstract class represents a command in the chatbot application.
 * All specific commands should extend this class and provide their implementation of the execute method.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The TaskList containing the current tasks.
     * @param ui      The Ui instance for user interaction and output.
     * @param storage The Storage instance for saving tasks or loading data.
     */
    public abstract UserCommand execute(TaskList tasks, Ui ui, Storage storage);
}
