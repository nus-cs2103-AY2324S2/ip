package jerry.command;

import jerry.TaskList;
import jerry.Ui;

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
