package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        for (Task t: tasks.getTasks()) {
            ui.showResult(t.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
