package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.Task;
import bond.task.TaskList;

/**
 * The AddCommand class is used to classify an add command in the Bond task.
 *
 * @author Benny Loh
 * @version 0.2
 */
public abstract class AddCommand extends Command {

    private final String taskName;

    /**
     * Constructor for the AddCommand class.
     *
     * @param commandType The type of command.
     * @param taskName    The name of the task.
     */
    public AddCommand(String commandType, String taskName) {
        super(commandType);
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Executes the add task command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The responses to user input.
     * @param storage The storage object.
     * @return The response message representing a successful execution of command.
     * @throws BondException If an error occurs during the execution of the command.
     */
    protected String addAndStore(TaskList tasks, Ui ui, Storage storage, Task task)
            throws BondException {
        tasks.addTask(task);
        String response = ui.taskAdded(task, tasks);
        storage.storeTask(task);
        return response;
    }
}
