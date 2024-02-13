package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.InvalidCommandException;

/**
 * Command to show that user has input an invalid command.
 */
public class InvalidCommand extends Command {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of InvalidCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @param storage The storage to write task into.
     * @throws InvalidCommandException If input is not valid.
     */
    public InvalidCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }


    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException("");
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
