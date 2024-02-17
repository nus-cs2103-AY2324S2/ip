package ben.commands;

import ben.exceptions.BenException;
import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a MarkCommand with the specified index to mark a task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand by marking the task at the specified index as done.
     *
     * @param tasks   The task list in which the task will be marked as done.
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

        tasks.markTask(index);

        return ui.showMarkedTaskMessage() +
                ui.showTask(tasks, index);
    }
}
