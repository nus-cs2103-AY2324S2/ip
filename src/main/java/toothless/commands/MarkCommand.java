package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Task;

/**
 * Represents a command to mark a specified task as done within the task list.
 * The task to be marked is specified by a detail string, which should contain the index of the task
 * in the task list.
 */
public class MarkCommand extends Command {
    private String detail;

    /**
     * Constructs a MarkCommand with the specified detail.
     * The detail is expected to be a string representing the index of the task to be marked as done.
     * @param detail The index of the task to be marked as done, as a string.
     */
    public MarkCommand(String detail) {
        this.detail = detail;
    }

    /**
     * Executes the command to mark a task as done. It converts the detail string to an index,
     * validates the index, retrieves the specified task from the list, marks the task as done,
     * and shows a message indicating the task has been marked as done.
     *
     * @param ui The user interface to interact with.
     * @param taskList The task list from which the task will be marked as done.
     * @param storage The storage system, not directly used by this command.
     * @return false to indicate the application should continue running.
     * @throws ToothlessException If the detail does not represent a valid index.
     */
    @Override
    public String handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        int taskIndex = super.getTaskIndex(detail);
        if (taskIndex >= taskList.size() || taskIndex < 0) {
            throw new ToothlessException(ui.showInvalidMarkWarning());
        }

        Task t = taskList.getTask(taskIndex);
        t.markAsDone();

        return ui.showMarkedTask(t);
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
