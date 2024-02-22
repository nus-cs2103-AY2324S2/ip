package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.TaskList;

/**
 * The ListCommand class is used to encapsulate a list command, which is
 * executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.1
 */
public class ListCommand extends Command {

    /**
     * Constructor for the ListCommand class.
     */
    public ListCommand() {
        super("list");
    }

    /**
     * Executes the list command.
     * Displays the list of tasks.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws BondException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        return tasks.noTasks() ? ui.showTaskListEmpty() : ui.showList(tasks);
    }

}
