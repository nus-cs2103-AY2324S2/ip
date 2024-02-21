package command;
import cleo.Task;
import cleo.TaskList;
import cleo.UI;
import cleo.DukeException;
public class AddCommand extends Command {
    private Task taskToAdd;
    private static final int MAX_TASKS = 100;
    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    @Override
    public String execute(TaskList tasks, UI ui) throws DukeException {
        if (tasks.size() >= MAX_TASKS) {
            return ui.showMaximumTasksReached();
        } else {
            tasks.addTask(taskToAdd);
            return ui.showTaskAdded(taskToAdd, tasks.size());
        }
    }
}
