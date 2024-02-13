package duke.command;

import duke.dukeexception.DukeException;
import duke.dukeexception.DukeInvalidArgument;
import duke.dukeexception.DukeMissingArgument;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class MarkCommand extends Command {
    private String inputs;

    public MarkCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (inputs.isEmpty()) {
            throw new DukeMissingArgument(1, "mark");
        }

        try {
            int index = Integer.valueOf(inputs);
            return tasks.mark(index);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgument(inputs);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
