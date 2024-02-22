package liv.processor;

import liv.container.Storage;
import liv.exception.LivException;
import liv.task.Task;
import liv.container.TaskList;
import liv.ui.Ui;

/**
 * Represents the command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * The constructor for the class.
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
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
        int trueIndex = index - 1;

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
