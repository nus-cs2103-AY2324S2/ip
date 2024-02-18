package inputcommands;

import snomexceptions.InvalidCommandException;
import snomtasklist.TaskList;

/**
 * The ListCommand implements the command of listing
 * tasks of the SnomBot.
 */
public class ListCommand extends Command {

    protected ListCommand() {
        super(" ");
    }

    @Override
    public CmdType getType() {
        return CmdType.LIST;
    }

    @Override
    public String execute(TaskList t) throws InvalidCommandException {
        return "list";
    }

}
