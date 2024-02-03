package duke.commands;

import duke.exceptions.InvalidCommandException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        storage.resetSave();
        storage.storeData(tasks);
    }
}
