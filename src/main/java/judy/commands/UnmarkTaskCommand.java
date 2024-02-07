package judy.commands;
import judy.type.Task;
import judy.type.TaskList;
import judy.storage.Storage;
import judy.ui.Ui;

public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private final Task task;
    private final TaskList taskList;

    public UnmarkTaskCommand(int taskId, TaskList taskList) {
        this.task = taskList.get(taskId);
        this.taskList = taskList;
    }
    @Override
    public void execute(Storage storage, Ui ui) {
        this.task.markAsUndone();
        storage.save(this.taskList.getTaskList());
        ui.showUnmarkTask(this.task);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
