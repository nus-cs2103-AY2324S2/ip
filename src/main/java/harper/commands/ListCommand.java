package harper.commands;

import harper.utils.Storage;
import harper.utils.TaskList;
import harper.utils.Ui;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printTasks(tasks.listTasks());
    }
}
