package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;
import duke.utils.DukeException;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws DukeException {
        storage.save(tasks);
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
