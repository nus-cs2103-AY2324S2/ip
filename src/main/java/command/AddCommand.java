package command;
import duke.Task;
import duke.TaskList;
import duke.UI;
import duke.DukeException;
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
