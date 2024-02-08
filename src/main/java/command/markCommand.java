package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

/**
 * Command to mark a specific task in the task list.
 */
public class MarkCommand extends Command {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public MarkCommand(TaskList taskList, Ui ui, Storage storage) {

        super(taskList, ui, storage);
    }

    /**
     * Call unmark function
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @throws Exception If input is not valid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String str;
        String input = ui.getInput();
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) - 1;
        str = taskList.mark(position);
        storage.writeTasks(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
