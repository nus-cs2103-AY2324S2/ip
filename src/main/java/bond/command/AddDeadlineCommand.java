package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.DeadlineTask;
import bond.task.Task;
import bond.task.TaskList;

/**
 * The AddDeadlineCommand class is used to encapsulate an add deadline task
 * command, which is executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class AddDeadlineCommand extends AddCommand {

    private String deadline;

    /**
     * Constructor for the AddDeadlineCommand class.
     *
     * @param taskName The name of the deadline task.
     * @param deadline The end datetime of the task.
     */
    public AddDeadlineCommand(String taskName, String deadline) {
        super("deadline", taskName);
        this.deadline = deadline;
    }

    /**
     * Executes the add deadline task command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage object.
     * @throws BondException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        Task newTask = new DeadlineTask(super.getTaskName(), this.deadline);
        return super.addAndStore(tasks, ui, storage, newTask);
    }

}
