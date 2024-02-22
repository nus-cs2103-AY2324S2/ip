package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.TaskList;

/**
 * The InvalidCommand class is used to encapsulate any invalid command, which is
 * executed upon invoking the execute() method.
 * Upon executing the invalid command, the user interface will display an error message.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class InvalidCommand extends Command {

    /**
     * Constructor for the InvalidCommand class.
     */
    public InvalidCommand() {
        super("invalid");
    }

    /**
     * Executes the invalid command.
     * Displays an error message.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws BondException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        BondException.raiseException("", "INVALID_COMMAND");
        return "";
    }
}
