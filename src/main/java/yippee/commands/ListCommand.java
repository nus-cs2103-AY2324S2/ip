package yippee.commands;
import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;

public class ListCommand extends Command {
    private boolean isExit = false;
    public ListCommand() {
        super(false);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        tasks.printList();
    }
}
