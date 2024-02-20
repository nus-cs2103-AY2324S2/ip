package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.Task;
import bond.task.TaskList;
import bond.task.TodoTask;

/**
 * The AddToDoCommand class is used to encapsulate an add todo task
 * command, which is executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class AddTodoCommand extends AddCommand {

    /**
     * Constructor for the AddToDoCommand class.
     *
     * @param taskName The name of the todo task.
     */
    public AddTodoCommand(String taskName) {
        super("todo", taskName);
    }

    /**
     * Executes the add todo task command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage object.
     * @throws BondException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        Task newTask = new TodoTask(super.getTaskName());
        return super.addAndStore(tasks, ui, storage, newTask);
    }

}
