package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("No task in list.\n"
                    + "\t You may add task with keywords: todo, deadline, event.");
        } else {
            ui.showTasks(list);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
