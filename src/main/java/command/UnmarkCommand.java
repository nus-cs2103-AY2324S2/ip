package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.EmptyInputException;

/**
 * Command to unmark a specific task in the task list.
 */
public class UnmarkCommand extends Command {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor for UnmarkCommand
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @param storage The storage to write task into.
     */
    public UnmarkCommand(TaskList taskList, Ui ui, Storage storage) {

        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String input = ui.getInput();
        if (input.split(" ").length <= 1) {
            throw new EmptyInputException("mark position");
        }
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) - 1;
        String str = taskList.unmark(position);
        storage.writeTasks(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
