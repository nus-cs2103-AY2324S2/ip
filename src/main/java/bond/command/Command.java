package bond.command;

import java.util.ArrayList;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.TaskList;

/**
 * The AddToDoCommand class is used to encapsulate an add todo task
 * command, which is executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.2
 */
public abstract class Command {

    public static final ArrayList<String> COMMANDS = new ArrayList<>() {
        {
            add("todo");
            add("deadline");
            add("event");
            add("list");
            add("mark");
            add("unmark");
            add("bye");
            add("delete");
            add("find");
        }
    };

    private final String commandType;

    /**
     * Constructor for the Command class.
     *
     * @param commandType The type of command.
     */
    public Command(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Executes the command.
     * Actual behaviour of the command is implemented in the subclasses.
     * Any changes to the task list will be reflected in the storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws BondException If an error occurs during the execution of the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BondException;

    public String getCommandType() {
        return this.commandType;
    }
}
