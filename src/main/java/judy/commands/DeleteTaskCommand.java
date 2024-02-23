package judy.commands;

import judy.storage.Storage;
import judy.task.Task;
import judy.task.TaskList;
import judy.ui.Ui;

/**
 * To delete a specific {@link Task} from the {@link TaskList}.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final Task task;
    private final TaskList taskList;

    /**
     * Constructs a DeleteTaskCommand with the specified task ID and task list.
     *
     * @param taskId   The ID of the task to be deleted.
     * @param taskList The list from which the task will be deleted.
     */
    public DeleteTaskCommand(int taskId, TaskList taskList) {
        this.task = taskList.get(taskId);
        this.taskList = taskList;
    }

    /**
     * Executes the command by removing the task from the task list, displaying a deletion message,
     * and saving the updated task list to the storage.
     *
     * @param storage The storage component responsible for saving the task list.
     * @param ui      The user interface component for displaying messages to the user.
     */

    @Override
    public void execute(Storage storage, Ui ui) {
        this.taskList.remove(this.task);
        storage.save(this.taskList.getTaskList());
        ui.showDeleteTask(this.task, this.taskList.getSize());
    }
    /**
     * Indicates whether this command represents an exit command.
     * In this case, always returns false as deleting a task does not trigger an exit.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
