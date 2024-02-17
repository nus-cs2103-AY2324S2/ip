package jerry.command;

import jerry.TaskList;
import jerry.Ui;

public class ListCommand extends Command {
    public ListCommand(Ui ui, TaskList tasks) {
        super(ui, tasks);
    }

    @Override
    public String execute() {
        return ui.showList(tasks);
    }
}
