package johnny.commands;

import java.util.List;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.Task;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

/**
 * Controls what happens when a Task is being unmarked.
 */
public class UnmarkCommand extends Command {

    /** Indices of Task in TaskList being unmarked. */
    private List<Integer> indices;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param indices Indices of Task in TaskList being unmarked.
     */
    public UnmarkCommand(List<Integer> indices) {
        this.indices = indices;
    }

    /**
     * Executes the process of a UnmarkCommand.
     * Unmark Tasks, rewrite TaskList to Storage and Ui shows appropriate response.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui to print responses.
     * @param storage Storage for data.
     * @throws JohnnyException If data cannot be written to Storage or Task does not exist in TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        List<Task> unmarkedTasks = tasks.unmark(indices);
        storage.rewriteFile(tasks);
        ui.showUnmark(unmarkedTasks);
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
