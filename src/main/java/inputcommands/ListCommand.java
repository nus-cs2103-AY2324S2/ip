package inputcommands;

import snomexceptions.InvalidCommandException;
import snomtasklist.TaskList;

/**
 * The ByeCommand implements the command of listing
 * out all the tasks in the Tasklist.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     * In this subclass, the command will return enum
     * of type LIST
     *
     * @return the enum of type LIST
     */
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



    /**
     * This method returns the string to list the tasks in TaskList.
     * @param t is the instance of Storage.TaskList.TaskList containing all the tasks.
     * @return a string representing the command to terminate the execution of SnomBot.
     * @throws InvalidCommandException
     */
    @Override
    public String execute(TaskList t) throws InvalidCommandException {
        return "list";
    }

}
