package command;
import duke.Ui;
import duke.TaskList;

/**
 * Command to show user the full task list.
 */
public class showListCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of showListCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     */
    public showListCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws Exception {
        taskList.showList();
    }

    @Override
    public boolean isExit() {
       return false;
    }
}
