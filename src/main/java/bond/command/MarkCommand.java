package command;

import bond.BondException;
import bond.Storage;
import bond.Ui;
import task.Task;
import task.TaskList;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        super("mark");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        if (this.index >= tasks.numberOfTasks()) {
            BondException.raiseException("delete", "INVALID_INDEX");
        }
        Task markedTask = tasks.getTask(index);
        markedTask.markAsComplete();
        ui.taskMarked(markedTask, tasks);
        storage.overwritePreviousSave(tasks);
    }

}
