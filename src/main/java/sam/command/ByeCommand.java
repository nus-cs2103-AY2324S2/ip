package sam.command;

import sam.SamException;
import sam.Storage;
import sam.TaskList;


/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) throws SamException {
        return "Hope you enjoy my help.";
    }

    public boolean isExit() {
        return true;
    }
}
