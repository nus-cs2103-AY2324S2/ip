package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.EmptyTimeException;

/**
 * Command is an abstract class that performs the command the user types.
 */
public abstract class Command {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of Command.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @param storage The storage to write task into.
     */
    public Command(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, EmptyTimeException;

    public abstract boolean isExit();
}
