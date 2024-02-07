package judy.commands;
import judy.task.Task;
import judy.task.TaskList;
import judy.storage.Storage;
import judy.ui.Ui;

public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final Task task;
    private final TaskList taskList;

    public DeleteTaskCommand(int taskId, TaskList taskList) {
        this.task = taskList.get(taskId);
        this.taskList = taskList;
    }

    @Override
    public void execute(Storage storage, Ui ui) {
        this.taskList.remove(this.task);
        storage.save(this.taskList.getTaskList());
        ui.showDeleteTask(this.task, this.taskList.getSize());
    }
    @Override
    public boolean isExit() {
        return false;
    }

}
