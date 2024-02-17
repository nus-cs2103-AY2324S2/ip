package Jerry.command;

import Jerry.TaskList;
import Jerry.Ui;

public class ListCommand extends Command {
    public ListCommand(Ui ui, TaskList tasks) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
    }

    @Override
    public String execute() {
        return ui.showList(tasks);
    }
}
