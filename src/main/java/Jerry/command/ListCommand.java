package Jerry.command;

import Jerry.TaskList;
import Jerry.Ui;

public class ListCommand extends Command {
    public ListCommand(Ui ui, TaskList tasks) {
        super(ui, tasks);
    }

    @Override
    public void execute() {
        ui.showList(tasks);
    }
}
