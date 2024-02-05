package inputcommands;

import snomexceptions.InvalidCommandException;
import snomtasklist.TaskList;


public class ByeCommand extends Command {

    private ByeCommand(String desc) {
        super(desc);
    }

    protected ByeCommand() {
        super(" ");
    }

    @Override
    public CmdType getType() {
        return CmdType.BYE;
    }

    @Override
    public String execute(TaskList t) throws InvalidCommandException {
        return "bye";
    }
}
