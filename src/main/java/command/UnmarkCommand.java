package command;

import data.TaskList;
import data.exception.CoDriverException;
import storage.Storage;
import ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        tasks.unmarkTask(index);
        ui.showUnmarkTask(tasks.get(index));
    }
}
