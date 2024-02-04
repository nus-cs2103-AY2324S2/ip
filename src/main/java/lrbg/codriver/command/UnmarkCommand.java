package lrbg.codriver.command;

import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        tasks.unmarkTask(index);
        ui.showUnmarkTask(tasks.get(index - 1));
    }

    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof UnmarkCommand) {
            UnmarkCommand other = (UnmarkCommand) obj;
            return other.index == this.index;
        } else {
            return false;
        }
    }
}
