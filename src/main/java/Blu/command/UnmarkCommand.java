package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.Task;
import blu.task.TaskList;
import blu.ui.UI;

/**
 * Represents a command to unmark a task in the task list with a specified index.
 */
public class UnmarkCommand extends Command {
    private final int taskIdx;

    /**
     * Constructs an UnmarkCommand to unmark the task at the specified index.
     *
     * @param taskIdx The index of the task to be unmarked.
     */
    public UnmarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Executes the unmark command.
     * This method retrieves the task at the specified index, changes its status to 'not completed',
     * notfies the user through UI, and saves the updated task list to storage.
     *
     * @param taskList The TaskList from which the task's status will be changed.
     * @param storage The Storage where the updated task list is to be saved.
     * @param ui The UI responsible for user interactions.
     * @return The message to be displayed to the user after unmarking a task.
     * @throws BluException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        Task task = taskList.getTask(taskIdx);
        if (!task.isCompleted()) {
            return ui.showTaskAlreadyUnmarked(taskIdx);
        }
        task.setUnmarked();
        storage.saveTasks(taskList);
        return ui.showTaskUnmarked(task);
    }
}
