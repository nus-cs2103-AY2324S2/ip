package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.Task;
import bond.task.TaskList;

/**
 * The MarkCommand class is used to encapsulate a mark task command, which is
 * executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructor for the MarkCommand class.
     *
     * @param index The index of the task to be marked as complete.
     */
    public MarkCommand(int index) {
        super("mark");
        this.index = index;
    }

    /**
     * Executes the mark task command.
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
        markedTask.markAsComplete();
        String response = ui.taskMarked(markedTask);
        storage.overwritePreviousSave(tasks);

        return response;
    }

}
