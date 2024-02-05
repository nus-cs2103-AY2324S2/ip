package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidCommandException;

public class MarkCommand extends Command {
    private boolean isUnmarkCommand;
    private boolean isExit = false;
    private int number;

    public MarkCommand(boolean isUnmark, int number) {
        super(false);
        this.isUnmarkCommand = isUnmark;
        this.number = number;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (isUnmarkCommand) {
            tasks.unmarkTask(number);
        } else {
            tasks.markTask(number);
        }
    }
}
