package lrbg.codriver.command;

import lrbg.codriver.data.Task;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        Task task = tasks.get(index);
        tasks.deleteTask(index);
        ui.showDeleteTask(task, tasks.size());
    }

    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof DeleteCommand) {
            DeleteCommand other = (DeleteCommand) obj;
            return other.index == this.index;
        } else {
            return false;
        }
    }
}
