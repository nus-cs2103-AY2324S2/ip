package tyler.command;
import tyler.task.TaskList;
import tyler.storage.Storage;
import tyler.ui.Ui;
import tyler.task.Todo;

/**
 *
 */
public class TodoCommand extends AddCommand {
    public TodoCommand(String taskName) {
        super(taskName);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTask = new Todo(this.taskName);
        tasks.addTask(newTask);
        int num = tasks.getNumOfTasks();
        ui.showTaskAdded(newTask, num);
    }
}
