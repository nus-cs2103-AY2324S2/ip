package command;
import cleo.TaskList;
import cleo.UI;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, UI ui) {
        return ui.showTaskList(tasks.getAllTasks());
    }
}
