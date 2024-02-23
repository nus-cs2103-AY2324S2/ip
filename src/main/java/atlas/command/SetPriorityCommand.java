package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.exception.AtlasException;

/**
 * This command handles the changing of priority of a task from the task list.
 */
public class SetPriorityCommand extends Command {
    private int taskIndex;
    private int priority;

    /**
     * Creates a SetPriorityCommand with the specified task index and a priority to be updated.
     *
     * @param tasks     The {@code TaskList} from which the task's priority will be updated.
     * @param ui        The {@code Ui} instance for user interaction.
     * @param storage   The {@code Storage} instance for saving the updated task list.
     * @param taskIndex The index of the task to be updated
     */
    public SetPriorityCommand(TaskList tasks, Ui ui, Storage storage, int taskIndex, int priority) {
        super(tasks, ui, storage);
        this.taskIndex = taskIndex;
        this.priority = priority;
    }

    /**
     * Executes the update of priority of the task at the specified index. It will display a message
     * indicating the task's priority has been updated
     *
     * @throws AtlasException If the taskIndex is out of bounds of the task list.
     */
    @Override
    public String execute() throws AtlasException {
        tasks.changeTaskPriority(taskIndex, this.priority);
        return ui.showTaskPriority(tasks, taskIndex);

    }
}
