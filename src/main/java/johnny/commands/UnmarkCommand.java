package johnny.commands;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.Task;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

/**
 * Controls what happens when a Task is being unmarked.
 */
public class UnmarkCommand extends Command {

    /** Index of Task in TaskList being unmarked. */
    private int index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param index Index of Task in TaskList being unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the process of a UnmarkCommand.
     * Unmarks Task, rewrite TaskList to Storage and Ui shows appropriate response.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui to print responses.
     * @param storage Storage for data.
     * @throws JohnnyException If data cannot be written to Storage or Task does not exist in TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        Task task = tasks.unmark(index);
        storage.rewriteFile(tasks);
        ui.showUnmark(task);
    }

    /**
     * Does not exit so chatbot can continue running.
     *
     * @return False so the loop keeps running.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
