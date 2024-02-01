package command;
import duke.Ui;
import duke.TaskList;

/**
 * Command is an abstract class that performs the command the user types.
 */
public abstract class Command {

    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of Command.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     */
    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws Exception;

    public abstract boolean isExit();
}
