package liv.processor;

import java.util.ArrayList;
import liv.container.Storage;
import liv.exception.LivException;
import liv.task.Task;
import liv.container.TaskList;
import liv.ui.Ui;

/**
 * Represents the command to delete tasks from the list.
 */
public class DeleteCommand extends Command {
    private final ArrayList<Integer> indices;

    /**
     * The constructor for the class.
     * @param indices The indices of the tasks to be deleted.
     */
    public DeleteCommand(ArrayList<Integer> indices) {
        this.indices = indices;
    }

    /**
     * Removes the task from the list and signals the Ui to respond.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The Ui to gives interaction with users.
     * @param storage The storage where the data is stored.
     * @throws LivException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LivException {

        Task removed = tasks.deleteTask(trueIndex);
        String message = Ui.getDeleteMessage(removed);
        storage.saveTaskToFile();
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChangedData() {
        return true;
    }
}
