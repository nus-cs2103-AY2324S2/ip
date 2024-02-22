package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Task;

/**
 * Represents a command to delete a specific task from the task list.
 * The task to be deleted is specified by a detail string,
 * which should contain the index of the task in the task list.
 */
public class DeleteCommand extends Command {
    private String detail;

    /**
     * Constructs a DeleteCommand with the specified detail.
     * The detail is expected to be a string representing the index of the task to be deleted.
     * @param detail The index of the task to be deleted, as a string.
     */
    public DeleteCommand(String detail) {
        this.detail = detail;
    }

    /**
     * Executes the command to delete a task from the task list. It converts the detail string to an index,
     * validates the index, removes the specified task from the list, and shows a message indicating the task
     * has been removed.
     * @param ui The user interface to interact with.
     * @param taskList The task list to be manipulated or queried.
     * @param storage The storage system for loading or saving tasks.
     * @return String message to be displayed to the user.
     * @throws ToothlessException If the detail does not represent a valid index.
     */
    @Override
    public String handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        int taskIndex = getTaskIndex(detail);
        if (taskIndex >= taskList.size() || taskIndex < 0) {
            throw new ToothlessException(ui.showInvalidDeleteWarning());
        }

        Task t = taskList.getTask(taskIndex);
        taskList.removeTask(taskIndex);

        return ui.showDeletedTask(t, taskList.size());
    }

    /**
     * Indicates whether the command is an exit command.
     * @return False, as the command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
