package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

/**
 * Command to exit the program.
 */
public class ByeCommand extends Command {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of byeCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @param storage The storage to write task into.
     * @throws DukeException If input is not valid.
     */
    public ByeCommand(TaskList taskList, Ui ui, Storage storage) {

        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.bye(ui);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
