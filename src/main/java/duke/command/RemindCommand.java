package duke.command;

import duke.dukeexception.DukeException;
import duke.dukeexception.DukeInvalidArgument;
import duke.dukeexception.DukeMissingArgument;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class RemindCommand extends Command {

    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.remind();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
