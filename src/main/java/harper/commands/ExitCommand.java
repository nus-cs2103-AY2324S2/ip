package harper.commands;

import harper.utils.Storage;
import harper.utils.TaskList;
import harper.utils.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.exit();
    }
}
