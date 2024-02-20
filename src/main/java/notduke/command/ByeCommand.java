package notduke.command;

import notduke.notdukeexception.NotDukeException;
import notduke.storage.Storage;
import notduke.tasklist.TaskList;

public class ByeCommand extends Command {

    public String execute(TaskList tasks, Storage storage) throws NotDukeException {
        storage.save(tasks);
        return "Bye. Hope to see you again soon!";
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
