package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class ListCommand extends Command {
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.list();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
