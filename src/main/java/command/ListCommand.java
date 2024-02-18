package command;
import duke.TaskList;
import duke.UI;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, UI ui) {
        return ui.showTaskList(tasks.getAllTasks());
    }
}
