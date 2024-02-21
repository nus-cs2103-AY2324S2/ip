package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.Task;
import bond.task.TaskList;

/**
 * The DeleteCommand class is used to encapsulate a delete task
 * command, which is executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructor for the DeleteCommand class.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        super("delete");
        this.index = index;
    }

    /**
     * Executes the delete task command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws BondException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        if (tasks.noTasks()) {
            BondException.raiseException("delete", "EMPTY_LIST");
        } else if (this.index < 0 || this.index >= tasks.numberOfTasks()) {
            BondException.raiseException("delete", "INVALID_INDEX");
        }
        Task removedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        String response = ui.taskDeleted(removedTask, tasks);
        storage.overwritePreviousSave(tasks);
        return response;
    }

}
