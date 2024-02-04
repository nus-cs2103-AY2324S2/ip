package InputCommands;

import SnomExceptions.InvalidCommandException;
import SnomTaskList.TaskList;

public class ListCommand extends Command {

    @Override
    public CmdType getType() {
        return CmdType.LIST;
    }


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
