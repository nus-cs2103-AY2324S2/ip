package liv.processor;

import java.util.ArrayList;
import liv.container.Storage;
import liv.exception.LivException;
import liv.task.Task;
import liv.container.TaskList;
import liv.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final ArrayList<Integer> indices;

    /**
     * The constructor of the class.
     * @param indices The index of the task to be marked as done.
     */
    public MarkCommand(ArrayList<Integer> indices) {
        this.indices = indices;
    }

    /**
     * Marks a task as done.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The Ui to gives interaction with users.
     * @param storage The storage where the data is stored.
     * @throws LivException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LivException {
        ArrayList<Task> markedTasks = new ArrayList<>();

        for (int index: indices) {
            Task task = TaskList.getTask(index);
            boolean currentState = task.getStatus();
            if (currentState) {
                throw new LivException("Mission number " + (index + 1) + " is already marked!");
            }
            task.markAsDone();
            markedTasks.add(task);
        }

        String message = Ui.getMarkMessage(markedTasks);

        return message;
    }
}
