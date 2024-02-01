package command;

import data.TaskList;
import data.exception.CoDriverException;
import storage.Storage;
import ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        tasks.markTask(index);
        ui.showMarkTask(tasks.get(index));
    }
}
