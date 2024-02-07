package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.Task;
import blu.task.TaskList;
import blu.ui.UI;

/**
 * Represents a command to deletes a specified task from the task list at a specified index.
 */
public class DeleteCommand extends Command {
    private final int taskIdx;

    /**
     * Constructs a DeleteCommand to delete the task at the specified index.
     *
     * @param taskIdx The index of the task to be deleted.
     */
    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Executes the delete command.
     * This method retrieves and removes the task at the specified index from the task list,
     * notifies the user through UI, and saves the updated task list to storage.
     *
     * @param taskList The TaskList from which the task will be deleted.
     * @param storage The Storage where the updated task list is to be saved.
     * @param ui The UI responsible for user interactions.
     * @return The message to be displayed to the user after deleting a task.
     * @throws BluException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        Task task = taskList.getTask(taskIdx);
        taskList.deleteTask(taskIdx);
        storage.saveTasks(taskList);
        return ui.showTaskDeleted(task, taskList);
    }
}
