package johnny.commands;

import java.util.List;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.Task;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

/**
 * Controls what happens when a Task is being marked.
 */
public class MarkCommand extends Command {

    /** Indices of Task in TaskList being marked. */
    private List<Integer> indices;

    /**
     * Constructor for MarkCommand.
     *
     * @param indices Indices of Task in TaskList being marked.
     */
    public MarkCommand(List<Integer> indices) {
        this.indices = indices;
    }

    /**
     * Executes the process of a MarkCommand.
     * Mark Tasks, rewrite TaskList to Storage and Ui shows appropriate response.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui to print responses.
     * @param storage Storage for data.
     * @throws JohnnyException If data cannot be written to Storage or Task does not exist in TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        List<Task> markedTasks = tasks.mark(indices);
        storage.rewriteFile(tasks);
        ui.showMark(markedTasks);
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
