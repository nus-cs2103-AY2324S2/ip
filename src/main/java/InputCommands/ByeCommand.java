package InputCommands;

import SnomExceptions.InvalidCommandException;
import SnomTaskList.TaskList;

/**
 * The ByeCommand implements the command of exiting
 * the SnomBot.
 */
public class ByeCommand extends Command {

    private ByeCommand(String desc) {
        super(desc);
    }

    protected ByeCommand() {
        super(" ");
    }

    /**
     * {@inheritDoc}
     * In this subclass, the command will return enum
     * of type BYE
     *
     * @return the enum of type BYE
     */
    @Override
    public CmdType getType() {
        return CmdType.BYE;
    }


    /**
     * This method returns the string to terminate the execution of SnomBot.
     * @param t is the instance of Storage.TaskList.TaskList containing all the tasks.
     * @return a string representing the command to terminate the execution of SnomBot.
     * @throws InvalidCommandException
     */
    @Override
    public String execute(TaskList t) throws InvalidCommandException {
        return "bye";
    }
}
