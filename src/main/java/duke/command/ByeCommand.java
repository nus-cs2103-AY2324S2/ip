package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class ByeCommand extends Command {

    public String execute(TaskList tasks, Storage storage) throws DukeException {
        storage.save(tasks);
        return "Bye. Hope to see you again soon!";
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
