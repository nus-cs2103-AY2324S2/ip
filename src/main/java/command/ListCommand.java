package command;
import duke.Ui;
import task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showList(tasks.getList());
    }
}
