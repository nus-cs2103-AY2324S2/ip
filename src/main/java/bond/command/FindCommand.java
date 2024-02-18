package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.TaskList;

/**
 * The FindCommand class is used to encapsulate a find task command, which is
 * executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructor for the FindCommand class.
     *
     * @param keyword The key word used to search for name of task.
     */
    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    /**
     * Executes the find task command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws BondException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        TaskList foundTasks = tasks.findTasks(this.keyword);
        return ui.showFoundTasks(foundTasks);

    }
}
