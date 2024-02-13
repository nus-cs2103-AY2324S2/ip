package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class FindCommand extends Command {

    private String inputs;
    public FindCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.find(inputs);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
