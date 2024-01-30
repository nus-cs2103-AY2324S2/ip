package Command;
import duke.TaskList;
import duke.UI;

public class ListCommand extends Command {
    @Override
    public TaskList execute(TaskList tasks, UI ui) {
        ui.showTaskList(tasks.getAllTasks());
        return tasks;
    }
}
