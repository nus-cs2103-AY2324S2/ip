package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class OtherCommand extends Command {

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printInvalidFeature();
    }
}
