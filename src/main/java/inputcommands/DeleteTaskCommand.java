package inputcommands;


import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandIndexException;
import snomtasklist.TaskList;


/**
 * The DeleteTaskCommand implements the command of deleting
 * a task in the TaskList.
 *
 */
class DeleteTaskCommand extends Command {

    protected DeleteTaskCommand(String desc) {
        super(desc);
    }

    /**
     * {@inheritDoc}
     * In this subclass, the command will return enum
     * of type DELETE
     *
     * @return the enum of type DELETE
     */
    @Override
    public CmdType getType() {
        return CmdType.DELETE;
    }

    /**
     * {@inheritDoc}
     * In this subclass, we implement index checks to ensure that the command
     * is valid.
     *
     * @param lst is the instance of Storage.TaskList.TaskList containing all the tasks.
     * @return a string representing a valid command.
     * @throws InvalidCommandException if the index is not valid for the tasklist.
     */
    @Override
    public String execute(TaskList lst) throws InvalidCommandIndexException {
        try {
            int index = Integer.parseInt(this.desc.split(" ")[1]);
            lst.getTaskAtIndex(index);
            return Integer.toString(index);
        } catch (InvalidCommandIndexException e) {
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandIndexException();
        } catch (NumberFormatException e) {
            throw new InvalidCommandIndexException();
        }

    }
}
