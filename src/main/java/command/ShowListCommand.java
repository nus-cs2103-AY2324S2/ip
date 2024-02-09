package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

/**
 * Command to show user the full task list.
 */
public class ShowListCommand extends Command {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public ShowListCommand(TaskList taskList, Ui ui, Storage storage) {

        super(taskList, ui, storage);
    }

    /**
     * show the full task list
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @throws Exception If input is not valid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.showList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
