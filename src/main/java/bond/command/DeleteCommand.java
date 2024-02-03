package command;

import bond.BondException;
import bond.Storage;
import bond.Ui;
import task.Task;
import task.TaskList;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        super("delete");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        if (tasks.noTasks()) {
            BondException.raiseException("delete", "EMPTY_LIST");
        } else if (this.index >= tasks.numberOfTasks()) {
            BondException.raiseException("delete", "INVALID_INDEX");
        }
        Task removedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.taskDeleted(removedTask, tasks);
        storage.overwritePreviousSave(tasks);
    }

}
