package harper.commands;

import harper.utils.TaskList;
import harper.utils.Ui;
import harper.utils.Storage;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks.listTasks());
    }
}
