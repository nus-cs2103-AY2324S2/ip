package liv.processor;

import java.util.ArrayList;
import liv.container.Storage;
import liv.exception.LivException;
import liv.task.Task;
import liv.container.TaskList;
import liv.ui.Ui;

/**
 * Represents a command that unmark a task (mark it as not done).
 */
public class UnmarkCommand extends Command {
    private final ArrayList<Integer> indices;

    /**
     * Constructor of the class.
     * @param indices The index of the task to be unmark.
     */
    public UnmarkCommand(ArrayList<Integer> indices) {
        this.indices = indices;
    }

    /**
     * Unmarks the task and signals the Ui to respond.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The Ui to gives interaction with users.
     * @param storage The storage where the data is stored.
     * @throws LivException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LivException {
        ArrayList<Task> unmarkedTasks = new ArrayList<>();

        for (int index: indices) {
            Task task = TaskList.getTask(index);
            boolean currentState = task.getStatus();
            if (!currentState) {
                throw new LivException("Mission number " + (index + 1) + " is already unmarked!");
            }
        }

        for (int index: indices) {
            Task task = TaskList.getTask(index);
            task.markAsNotDone();
            unmarkedTasks.add(task);
        }

        String message = Ui.getUnmarkMessage(indices, unmarkedTasks);

        storage.saveTaskToFile();

        return message;
    }
}
