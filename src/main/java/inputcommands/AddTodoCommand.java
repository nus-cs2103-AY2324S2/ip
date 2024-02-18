package inputcommands;
import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandTaskDescException;
import snomtasklist.TaskList;


/**
 * The AddTodoCommand implements the command of adding
 * a task of type Todo to the TaskList.
 */
class AddTodoCommand extends Command {

    protected AddTodoCommand(String desc) {
        super(desc);
    }

    /**
     * {@inheritDoc}
     * In this subclass, the command will return enum
     * of type TODO
     *
     * @return the enum of type TODO
     */
    @Override
    public CmdType getType() {
        return CmdType.TODO;
    }

    /**
     * {@inheritDoc}
     * In this subclass, we implement task description checks
     * to ensure that the command is valid
     *
     * @param lst is the instance of Storage.TaskList.TaskList containing all the tasks.
     * @return a string representing a valid command.
     * @throws InvalidCommandException if the task description is invalid
     *     (such as being blank)
     */
    @Override
    public String execute(TaskList lst) throws InvalidCommandTaskDescException {
        try {
            String detail = this.desc.toLowerCase().split("todo ", 2)[1].trim();
            if (detail.isEmpty()) {
                throw new InvalidCommandTaskDescException();
            }
            return detail;
        } catch (ArrayIndexOutOfBoundsException e) {

            throw new InvalidCommandTaskDescException();
        }

    }



}
