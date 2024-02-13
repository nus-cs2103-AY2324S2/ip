package duke.command;

import duke.dukeexception.DukeException;
import duke.dukeexception.DukeInvalidArgument;
import duke.dukeexception.DukeMissingArgument;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class DeleteCommand extends Command {
    private String inputs;

    public DeleteCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (inputs.isEmpty()) {
            throw new DukeMissingArgument(1, "delete");
        }

        try {
            int index = Integer.valueOf(inputs);
            return tasks.delete(index);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgument(inputs);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
