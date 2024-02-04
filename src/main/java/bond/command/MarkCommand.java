/**
 * The MarkCommand class is used to encapsulate a mark task command, which is
 * executed upon invoking the execute() method.
 * 
 * @author Benny Loh
 * @version 0.1
 */
package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.Task;
import bond.task.TaskList;

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
