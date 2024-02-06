package yippee.commands;
import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        storage.resetSave();
        storage.storeData(tasks);
    }
}
