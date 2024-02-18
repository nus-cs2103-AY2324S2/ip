package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.Task;
import bond.task.TaskList;

/**
 * The UnmarkCommand class is used to encapsulate an unmark task
 * command, which is executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructor for the UnmarkCommand class.
     *
     * @param index The index of the task to be marked as incomplete.
     */
    public UnmarkCommand(int index) {
        super("unmark");
        this.index = index;
    }

    /**
     * Executes the unmark task command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws BondException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        if (this.index >= tasks.numberOfTasks()) {
            BondException.raiseException("delete", "INVALID_INDEX");
        }

        Task markedTask = tasks.getTask(index);
        markedTask.markAsIncomplete();
        String response = ui.taskUnmarked(markedTask);
        storage.overwritePreviousSave(tasks);

        return response;
    }

}
