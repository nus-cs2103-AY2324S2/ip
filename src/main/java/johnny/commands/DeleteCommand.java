package johnny.commands;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.Task;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

/**
 * Controls what happens when a Task is being deleted.
 */
public class DeleteCommand extends Command {

    /** Index of Task in TaskList being deleted. */
    private int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index Index of Task in TaskList being deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the process of a DeleteCommand.
     * Deletes Task, rewrite TaskList to Storage and Ui shows appropriate response.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui to print responses.
     * @param storage Storage for data.
     * @throws JohnnyException If data cannot be written to Storage or Task does not exist in TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        Task task = tasks.delete(index);
        storage.rewriteFile(tasks);
        ui.showDelete(task, tasks);
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
