package yippee.commands;

import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;

public class StatsCommand extends Command {
    public StatsCommand() {
        super(false);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        ui.printStats();
    }

}
