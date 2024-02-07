package judy.commands;
import judy.task.Task;
import judy.task.TaskList;
import judy.storage.Storage;
import judy.ui.Ui;

public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private final Task task;
    private final TaskList taskList;

    public MarkTaskCommand(int taskId, TaskList taskList) {
        this.task = taskList.get(taskId);
        this.taskList = taskList;
    }
    @Override
    public void execute(Storage storage, Ui ui) {
        this.task.markAsDone();
        storage.save(this.taskList.getTaskList());
        ui.showMarkTask(this.task);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
