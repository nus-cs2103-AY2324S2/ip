package notduke.command;

import notduke.notdukeexception.NotDukeException;
import notduke.storage.Storage;
import notduke.tasklist.TaskList;

public class RemindCommand extends Command {

    public String execute(TaskList tasks, Storage storage) throws NotDukeException {
        return tasks.remind();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
