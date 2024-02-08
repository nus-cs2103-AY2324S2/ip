package command;
import duke.TaskList;
import duke.Ui;

/**
 * Command to show user the full task list.
 */
public class ShowListCommand extends Command {

    private TaskList taskList;
    private Ui ui;
    public ShowListCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * show the full task list
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @throws Exception If input is not valid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws Exception {
        taskList.showList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
