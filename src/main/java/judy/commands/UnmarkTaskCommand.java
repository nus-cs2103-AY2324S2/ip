package judy.commands;
import judy.task.Task;
import judy.task.TaskList;
import judy.storage.Storage;
import judy.ui.Ui;

/**
 * The UnmarkTaskCommand class represents a command to unmark
 * a specific {@link Task} as done in the {@link TaskList}.
 */
public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private final Task task;
    private final TaskList taskList;

    /**
     * Constructs an UnmarkTaskCommand with the specified task ID and task list.
     *
     * @param taskId   The ID of the task to be unmarked.
     * @param taskList The list containing the task to be unmarked.
     */
    public UnmarkTaskCommand(int taskId, TaskList taskList) {
        this.task = taskList.get(taskId);
        this.taskList = taskList;
    }

    /**
     * Executes the command by marking the specified task as undone, updating the storage,
     * and displaying a confirmation message.
     *
     * @param storage The storage component responsible for saving the task list.
     * @param ui      The user interface component for displaying messages to the user.
     */
    @Override
    public void execute(Storage storage, Ui ui) {
        this.task.markAsUndone();
        storage.save(this.taskList.getTaskList());
        ui.showUnmarkTask(this.task);
    }

    /**
     * Indicates whether this command represents an exit command.
     * In this case, always returns false as unmarking a task does not trigger an exit.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
