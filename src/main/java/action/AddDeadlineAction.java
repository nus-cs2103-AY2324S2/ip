package action;

import task.TaskList;

/**
 * AddDeadlineCommand encapsulates the behaviour of adding a deadline.
 *
 * @author Titus Chew
 */
public class AddDeadlineAction extends Action {
    /**
     * Constructor for this add deadline action.
     *
     * @param arguments the arguments supplied with the command
     */
    public AddDeadlineAction(Argument[] arguments) {
        super(Command.ADD_DEADLINE, arguments);
    }

    /**
     * Add a deadline task to the user's list.
     *
     * @param taskList the taskList to modify
     */
    @Override
    public void execute(TaskList taskList) {
        String name = findDefaultArgument(),
                by = findArgument("by");

        // Validate arguments
        if (name == null) {
            handleMissingArgument(getCommand(), "name");
            return;
        }
        if (by == null) {
            handleMissingArgument(getCommand(), "by");
            return;
        }

        // Perform behaviour
        taskList.addDeadline(name, by);
        handleAddSuccess(taskList);
    }
}
