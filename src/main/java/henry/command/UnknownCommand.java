package henry.command;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;
import henry.Ui;

/**
 * Represents a command that is not recognised.
 */
public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        throw new HenryException("I don't understand this command...");
    }
}
