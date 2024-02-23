package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.exception.AtlasException;

/**
 * This command handles marking a task as completed in the task list.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified TaskList, Ui, and Storage.
     * This command marks a task, identified by its index, as done.
     *
     * @param tasks     The TaskList containing the task to be marked.
     * @param ui        The Ui instance for user interaction.
     * @param storage   The Storage instance for saving the updated task list.
     * @param taskIndex The index of the task in the task list to be marked as done.
     */
    public MarkCommand(TaskList tasks, Ui ui, Storage storage, int taskIndex) {
        super(tasks, ui, storage);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the action of marking a task as done.
     * The task is marked in the task list and a confirmation message is displayed to the user.
     *
     * @throws AtlasException If an error occurs during marking the task (e.g., index out of bounds).
     */
    @Override
    public String execute() throws AtlasException {
        tasks.markTask(taskIndex);
        return ui.showMark(super.tasks, taskIndex);
    }
}
