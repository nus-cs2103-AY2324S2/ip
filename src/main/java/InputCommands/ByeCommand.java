package InputCommands;

import SnomExceptions.InvalidCommandException;
import TaskList.TaskList;


public class ByeCommand extends Command {

    private ByeCommand(String desc) {
        super(desc);
    }

    protected ByeCommand() {
        super(" ");
    }

    @Override
    public String execute(TaskList t) throws InvalidCommandException {
        return "bye";
    }
}
