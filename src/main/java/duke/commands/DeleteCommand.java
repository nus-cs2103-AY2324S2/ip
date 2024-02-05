package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidCommandException;
public class DeleteCommand extends Command {
    private int deleteNumber;
    public DeleteCommand(int number) {
        super(false);
        this.deleteNumber = number;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        tasks.deleteTask(this.deleteNumber);
    }

}
