package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to start the program.
 */
public class WelcomeCommand extends Command {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of WelcomeCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @param storage The storage to write task into.
     */
    public WelcomeCommand(TaskList taskList, Ui ui, Storage storage) {

        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.welcome(ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
