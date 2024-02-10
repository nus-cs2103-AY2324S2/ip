package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * A command to exit the program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null : "The ui must not be null";

        ui.showBye();
    }
}
