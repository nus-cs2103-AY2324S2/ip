package johnny.commands;

import java.util.List;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.Task;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

/**
 * Controls what happens when a Task is being deleted.
 */
public class DeleteCommand extends Command {

    /** Indices of Task in TaskList being deleted. */
    private List<Integer> indices;

    /**
     * Constructor for DeleteCommand.
     *
     * @param indices Indices of Tasks in TaskList being deleted.
     */
    public DeleteCommand(List<Integer> indices) {
        this.indices = indices;
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
        List<Task> deletedTasks = tasks.delete(indices);
        storage.rewriteFile(tasks);
        ui.showDelete(deletedTasks, tasks);
    }

    /**
     * Returns False so chatbot can continue running.
     *
     * @return False so the loop keeps running.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
