package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.Task;
import blu.task.TaskList;
import blu.ui.UI;

/**
 * Represents a command to mark a task in the task list with a specified index.
 */
public class MarkCommand extends Command {
    private final int taskIdx;

    /**
     * Constructs an MarkCommand to mark the task at the specified index.
     *
     * @param taskIdx The index of the task to be mark.
     */
    public MarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Executes the mark command.
     * This method retrieves the task at the specified index, changes its status to 'completed',
     * notfies the user through UI, and saves the updated task list to storage.
     *
     * @param taskList The TaskList from which the task's status will be changed.
     * @param storage The Storage where the updated task list is to be saved.
     * @param ui The UI responsible for user interactions.
     * @throws BluException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        Task task = taskList.getTask(taskIdx);
        boolean isMarked = task.getIsMarked();
        if (isMarked) {
            ui.showTaskAlreadyMarked(taskIdx);
        } else {
            task.setMarked();
            ui.showTaskMarked(task, taskIdx);
        }
        storage.saveTasks(taskList);
    }
    
}
