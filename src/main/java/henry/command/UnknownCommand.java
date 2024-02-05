package henry.command;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;

/**
 * Represents a command that is not recognised.
 */
public class UnknownCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) throws HenryException {
        throw new HenryException("I don't understand this command...");
    }
}
