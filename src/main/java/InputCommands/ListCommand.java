package InputCommands;

import SnomExceptions.InvalidCommandException;
import TaskList.TaskList;

public class ListCommand extends Command {


    private ListCommand(String desc) {
        super(desc);
    }

    protected ListCommand() {
        super (" ");
    }


    @Override
    public String execute(TaskList t) throws InvalidCommandException {
        return "list";
    }

}
