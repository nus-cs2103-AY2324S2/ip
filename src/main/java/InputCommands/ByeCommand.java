package InputCommands;

import SnomExceptions.InvalidCommandException;
import TaskList.TaskList;


public class ByeCommand extends Command {

    public String execute(TaskList t) throws InvalidCommandException {
        return "bye";
    }
}
