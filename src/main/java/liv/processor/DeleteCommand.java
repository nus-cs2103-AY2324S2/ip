package liv.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        ArrayList<Task> deletedTasks = new ArrayList<>();
        ArrayList<Integer> reversedIndices = new ArrayList<>();
        reversedIndices.addAll(indices);
        reversedIndices.sort(Comparator.reverseOrder());

        for (int index: reversedIndices) {
            Task deletedTask = tasks.deleteTask(index);
            deletedTasks.add(deletedTask);
        }

        Collections.reverse(deletedTasks);
        String message = Ui.getDeleteMessage(indices, deletedTasks);

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
