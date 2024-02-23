package notduke.command;

import notduke.notdukeexception.NotDukeException;
import notduke.storage.Storage;
import notduke.tasklist.TaskList;

public class FindCommand extends Command {

    private String inputs;
    public FindCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws NotDukeException {
        return tasks.find(inputs);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
