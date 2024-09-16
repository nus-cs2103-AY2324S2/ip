package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Task;

/**
 * Represents a command to mark a task as not done within the task list.
 * The task to be unmarked is specified by a detail string, which should contain the index of the task
 * in the task list.
 */
public class UnmarkCommand extends Command {
    private String detail;

    /**
     * Constructs an UnmarkCommand with the specified detail.
     * The detail is expected to be a string representing the index of the task to be marked as not done.
     * @param detail The index of the task to be marked as not done, as a string.
     */
    public UnmarkCommand(String detail) {
        this.detail = detail;
    }

    /**
     * Executes the command to mark a specified task as not done. It converts the detail string to an index,
     * validates the index, marks the specified task as not done, and shows a message indicating the task
     * has been marked as not done.
     *
     * @param ui The user interface to interact with.
     * @param taskList The task list from which the task will be marked as not done.
     * @param storage The storage system, not directly used by this command.
     * @return false to indicate the application should continue running.
     * @throws ToothlessException If the detail does not represent a valid index or points to a non-existent task,
     *  or if the detail string is empty.
     */
    @Override
    public String handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        int taskIndex = super.getTaskIndex(detail);
        if (taskIndex >= taskList.size() || taskIndex < 0) {
            throw new ToothlessException(ui.showInvalidUnmarkWarning());
        }

        Task t = taskList.getTask(taskIndex);
        t.markAsNotDone();

        return ui.showUnmarkedTask(t);
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
