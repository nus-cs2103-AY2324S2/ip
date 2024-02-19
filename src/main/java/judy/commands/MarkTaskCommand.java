package judy.commands;
import judy.task.Task;
import judy.task.TaskList;
import judy.storage.Storage;
import judy.ui.Ui;

/**
 * The MarkTaskCommand class represents a command to mark
 * a specific {@link Task} as done in the {@link TaskList}.
 */
public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private final Task task;
    private final TaskList taskList;

    /**
     * Constructs a MarkTaskCommand with the specified task ID and task list.
     *
     * @param taskId   The ID of the task to be marked as done.
     * @param taskList The list containing the task to be marked.
     */
    public MarkTaskCommand(int taskId, TaskList taskList) {
        this.task = taskList.get(taskId);
        this.taskList = taskList;
    }

    /**
     * Executes the command by marking the specified task as done, updating task list to the storage,
     * and displaying a confirmation message in the user interface.
     *
     * @param storage The storage component responsible for saving the task list.
     * @param ui      The user interface component for displaying messages to the user.
     */
    @Override
    public void execute(Storage storage, Ui ui) {
        this.task.markAsDone();
        storage.save(this.taskList.getTaskList());
        ui.showMarkTask(this.task);
    }

    /**
     * Indicates whether this command represents an exit command.
     * In this case, always returns false as marking a task as done does not trigger an exit.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
