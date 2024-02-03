package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String inputs;
    public FindCommand(String command) {
        inputs = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.sendReply(tasks.find(inputs));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
