package inputcommands;


import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandIndexException;
import snomtasklist.TaskList;

<<<<<<< HEAD:src/main/java/inputcommands/UnmarkTaskCommand.java
class UnmarkTaskCommand extends Command {
=======

/**
 * The UnmarkTaskCommand implements the command of unmarking
 * a task in the tasklist as done.
 *
 */
class UnmarkTaskCommand extends Command{
>>>>>>> branch-A-JavaDoc:src/main/java/InputCommands/UnmarkTaskCommand.java

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
     * @throws InvalidCommandException if the index is not valid for the tasklist.
     */
    @Override
    public String execute(TaskList lst) throws InvalidCommandIndexException {
        try {
            int pos = Integer.parseInt(this.desc.split(" ")[1]);
            lst.getTask(pos);
            return Integer.toString(pos);
        } catch (InvalidCommandIndexException e) {
            throw new InvalidCommandIndexException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandIndexException();
        }

    }


}
