package ben.commands;

import ben.exceptions.BenException;
import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates an UnmarkCommand with the specified index to unmark a task.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand by unmarking the task at the specified index.
     *
     * @param tasks   The task list in which the task will be unmarked.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     * @throws BenException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BenException {

        // check if task list is empty
        if (tasks.isEmpty()) {
            throw new BenException("   There are no pending tasks now... Add some tasks here!");
        }

        // check if input is within bounds
        if (tasks.isOutOfBounds(index)) {
            throw new BenException("   Please input a valid number between 1 and " + tasks.size());
        }

        tasks.unmarkTask(index);

        return ui.showUnmarkedTaskMessage() +
                ui.showTask(tasks, index);
    }
}
