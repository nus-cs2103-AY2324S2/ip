package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.EventTask;
import bond.task.Task;
import bond.task.TaskList;

/**
 * The AddEventCommand class is used to encapsulate an add event task
 * command, which is executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class AddEventCommand extends AddCommand {

    private String start;
    private String end;

    /**
     * Constructor for the AddEventCommand class.
     *
     * @param taskName The name of the event task.
     * @param start    The start datetime of the event.
     * @param end      The end datetime of the event.
     */
    public AddEventCommand(String taskName, String start, String end) {
        super("event", taskName);
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the add event task command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage object.
     * @throws BondException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        Task newTask = new EventTask(super.getTaskName(), this.start, this.end);
        return super.addAndStore(tasks, ui, storage, newTask);
    }

}
