package command;
import duke.Ui;
import duke.TaskList;
import exception.InvalidCommandException;

public class InvalidCommand extends Command {

    private TaskList taskList;
    private Ui ui;

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
