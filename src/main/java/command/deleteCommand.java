package command;
import duke.TaskList;
import duke.Ui;

/**
 * Command to delete a specific task from the task list.
 */
public class DeleteCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of deleteCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @throws Exception If input is not valid.
     */
    public DeleteCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws Exception {
        String input = ui.getInput();
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) - 1;
        taskList.delete(position);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
