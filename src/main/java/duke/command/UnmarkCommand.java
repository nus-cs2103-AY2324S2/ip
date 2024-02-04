package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;


public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index <= tasks.getTaskSize() && this.index > 0) {
            tasks.unmark(this.index);
            ui.showUnmarkMsg(tasks.getTasks().get(this.index - 1));
            storage.save(tasks);
        } else {
            throw new DukeException("Invalid index." +
                    "Please provide a valid index within the range 1 to " + tasks.getTaskSize() + ".");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
