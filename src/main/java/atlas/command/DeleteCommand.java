package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.exception.AtlasException;
import atlas.task.Task;

/**
 * This command handles the deletion of a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DeleteCommand with the specified task index to be deleted.
     *
     * @param tasks       The {@code TaskList} from which the task will be deleted.
     * @param ui          The {@code Ui} instance for user interaction.
     * @param storage     The {@code Storage} instance for saving the updated task list.
     * @param taskIndex   The index of the task to be deleted.
     */
    public DeleteCommand(TaskList tasks, Ui ui, Storage storage, int taskIndex) {
        super(tasks, ui, storage);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the deletion of the task at the specified index. It will display a message
     * indicating the task deleted and then remove the task from the list.
     *
     * @throws AtlasException If the taskIndex is out of bounds of the task list.
     */
    @Override
    public String execute() throws AtlasException {
        Task deletedTask = tasks.deleteTask(taskIndex);
        return ui.showTaskDeleted(deletedTask);
    }
}
