package command;
import duke.Ui;
import duke.TaskList;
import exception.InvalidCommandException;
import exception.InvalidDateTimeException;

/**
 * Command to show that user has input an invalid command.
 */
public class InvalidCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of InvalidCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @throws InvalidCommandException If input is not valid.
     */
    public InvalidCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        throw new InvalidCommandException("");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
