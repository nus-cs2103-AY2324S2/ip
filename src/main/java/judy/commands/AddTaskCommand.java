package judy.commands;
import judy.type.Task;
import judy.type.TaskList;
import judy.storage.Storage;
import judy.ui.Ui;
public class AddTaskCommand extends Command {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";

    public static final String EVENT = "event";
    private final Task task;
    private final TaskList taskList;

    public AddTaskCommand(Task task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }

    @Override
    public void execute(Storage storage, Ui ui) {
        taskList.add(this.task);
        ui.showAddTask(this.task, this.taskList.getSize());
        storage.save(taskList.getTaskList());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
