package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.sendReply(tasks.list());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
