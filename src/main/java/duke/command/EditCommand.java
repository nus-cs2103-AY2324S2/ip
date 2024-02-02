package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EditCommand extends Command{

    private final boolean isMark;
    private final int index;

    public EditCommand(boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Index out of bound handler
        if (index >= tasks.getItems().size()) {
            throw new DukeException("The index of task cannot be larger than number of task.");
        } else if (index < 0) {
            throw new DukeException("The index of task must be positive integer.");
        }

        if (this.isMark) {
            tasks.markDone(index);
            ui.mark(tasks.getItem(index));
        } else {
            tasks.unmarkDone(index);
            ui.mark(tasks.getItem(index));
        }

        storage.saveChanges(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
