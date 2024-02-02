package InputCommands;

import SnomExceptions.InvalidCommandException;
import Storage.TaskList;

public class ListCommand extends Command {

    public String execute(TaskList t) throws InvalidCommandException {
        return "list";
    }

}
