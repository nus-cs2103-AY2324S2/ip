package yippee.commands;
import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;
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
