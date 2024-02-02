package duke.command;

import duke.dukeexception.DukeException;
import duke.dukeexception.DukeInvalidArgument;
import duke.dukeexception.DukeMissingArgument;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private String inputs;

    public UnmarkCommand(String command) {
        inputs = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputs.isEmpty()) {
            throw new DukeMissingArgument(1,"unmark");
        }
        try {
            int index = Integer.valueOf(inputs);
            ui.sendReply(tasks.unmark(index));
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgument(inputs);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
