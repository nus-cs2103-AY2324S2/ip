package InputCommands;

import SnomExceptions.InvalidCommandException;
import Storage.TaskList;

public class ByeCommand extends Command {

    public String execute(TaskList t) throws InvalidCommandException {
        return "bye";
    }
}
