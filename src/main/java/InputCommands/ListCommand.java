package InputCommands;

import SnomExceptions.InvalidCommandException;
import TaskList.TaskList;

public class ListCommand extends Command {


    @Override
    public String execute(TaskList t) throws InvalidCommandException {
        return "list";
    }

}
