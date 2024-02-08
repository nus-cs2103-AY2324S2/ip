package command;
import duke.TaskList;
import duke.Ui;

/**
 * Command to exit the program.
 */
public class ByeCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of byeCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @throws Exception If input is not valid.
     */
    public ByeCommand(TaskList taskList, Ui ui) {

        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws Exception {
        taskList.bye(ui);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
