package liv.processor;

import liv.container.Storage;
import liv.exception.LivException;
import liv.task.Task;
import liv.container.TaskList;
import liv.ui.Ui;

/**
 * Represents a command that unmark a task (mark it as not done).
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructor of the class.
     * @param index The index of the task to be unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
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
        int trueIndex = index - 1;
        Task task = TaskList.getTask(trueIndex);
        boolean currentState = task.getStatus();
        if (!currentState) {
            throw new LivException("Mission already unmarked!");
        }
        task.markAsNotDone();
        String message = Ui.getUnmarkMessage(task);
        return message;
    }
}
