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
        ui.showBye();
    }
}
