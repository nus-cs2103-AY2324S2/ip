package cat.command;

import cat.Storage;
import cat.TaskList;
import cat.ui.Ui;

/**
 * A command for accessing help for the program.
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null : "Ui cannot be null for HelpCommand";
        ui.showHelp();
    }
}
