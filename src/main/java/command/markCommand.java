package command;
import duke.Ui;
import duke.TaskList;

/**
 * Command to mark a specific task in the task list.
 */
public class markCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of markCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @throws Exception If input is not valid.
     */
    public markCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }
    @Override

    public void execute(TaskList taskList, Ui ui) throws Exception {
        String input = ui.getInput();
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) -1;
        taskList.mark(position);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
