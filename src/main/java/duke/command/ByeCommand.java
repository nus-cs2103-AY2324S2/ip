package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to run Bye Command.
 *
 * @author KohGuanZeh
 */
public class ByeCommand extends Command {
    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException {
        ui.showFarewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
