package inputcommands;


import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandIndexException;
import snomtasklist.TaskList;




/**
 * The UnmarkTaskCommand implements the command of unmarking
 * a task in the TaskList as done.
 *
 */
class UnmarkTaskCommand extends Command {


    protected UnmarkTaskCommand(String desc) {
        super(desc);
    }

    /**
     * {@inheritDoc}
     * In this subclass, the command will return enum
     * of type UNMARK
     *
     * @return the enum of type UNMARK
     */
    @Override
    public CmdType getType() {
        return CmdType.UNMARK;
    }

    /**
     * {@inheritDoc}
     * In this subclass, we implement index checks to ensure that the command
     * is valid.
     *
     * @param lst is the instance of Storage.TaskList.TaskList containing all the tasks.
     * @return a string representing a valid command.
     * @throws InvalidCommandException if the index is not valid for the TaskList.
     */
    @Override
    public String execute(TaskList lst) throws InvalidCommandIndexException {
        try {
            int pos = Integer.parseInt(this.desc.split(" ")[1]);
            lst.getTaskAtIndex(pos);
            return Integer.toString(pos);
        } catch (InvalidCommandIndexException e) {
            throw new InvalidCommandIndexException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandIndexException();
        } catch (NumberFormatException e) {
            throw new InvalidCommandIndexException();
        }

    }


}
