package cat.command;

import cat.Storage;
import cat.TaskList;
import cat.ui.Ui;
import cat.ui.response.Response;

/**
 * A command for accessing help for the program.
 */
public class HelpCommand extends Command {
    @Override
    public Response execute(TaskList tasks, Storage storage) {
        return Ui.showHelp();
    }
}
