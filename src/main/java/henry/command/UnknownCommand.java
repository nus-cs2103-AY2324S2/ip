package henry.command;

import henry.Storage;
import henry.TaskList;
import henry.Ui;
import henry.HenryException;

/**
 * Represents a command that is not recognised.
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        throw new HenryException("I don't understand this command...");
    }
}
