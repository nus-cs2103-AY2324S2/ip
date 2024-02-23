package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.exception.AtlasException;

/**
 * This command handles unmarking a task as uncompleted in the task list.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a UnmarkCommand with the specified TaskList, Ui, and Storage.
     * This command unmarks a task, identified by its index, as incomplete.
     *
     * @param tasks     The TaskList containing the task to be unmarked.
     * @param ui        The Ui instance for user interaction.
     * @param storage   The Storage instance for saving the updated task list.
     * @param taskIndex The index of the task in the task list to be unmarked as incomplete.
     */
    public UnmarkCommand(TaskList tasks, Ui ui, Storage storage, int taskIndex) {
        super(tasks, ui, storage);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the action of unmarking a task as incomplete.
     * The task is unmarked in the task list and a incompletion message is displayed to the user.
     *
     * @throws AtlasException If an error occurs during unmarking the task (e.g., index out of bounds).
     */
    @Override
    public String execute() throws AtlasException {
        tasks.unmarkTask(taskIndex);
        return ui.showunMark(tasks, taskIndex);
    }
}
