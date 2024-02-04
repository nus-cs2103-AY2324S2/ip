/**
 * The DeleteCommand class is used to encapsulate a delete task
 * command, which is executed upon invoking the execute() method.
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
