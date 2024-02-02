package raphael.command;
import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
