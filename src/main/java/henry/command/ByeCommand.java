package henry.command;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) throws HenryException {
        return "See you again bro!";
    }

    public boolean isExit() {
        return true;
    }
}
