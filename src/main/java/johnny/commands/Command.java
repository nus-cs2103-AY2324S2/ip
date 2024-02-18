package johnny.commands;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

/**
 * Command classes inherit from this abstract class.
 */
public abstract class Command {

    /**
     * Executes the process of Commands.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui to print responses.
     * @param storage Storage for data.
     * @throws JohnnyException If data cannot be written to Storage or Task does not exist in TaskList.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException;

    /**
     * Determines if chatbot should continue running.
     *
     * @return Boolean to continue or stop the loop.
     */
    public abstract boolean isExit();

}
