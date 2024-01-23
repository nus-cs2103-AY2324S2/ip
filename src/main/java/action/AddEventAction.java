package action;

import task.TaskList;

/**
 * AddEventCommand encapsulates the behaviour of adding an event.
 *
 * @author Titus Chew
 */
public class AddEventAction extends Action {
    /**
     * Constructor for this add event action.
     *
     * @param arguments the arguments supplied with the command
     */
    public AddEventAction(Argument[] arguments) {
        super(Command.ADD_EVENT, arguments);
    }

    /**
     * Add an event to the task list.
     *
     * @param taskList the taskList to modify
     */
    @Override
    public void execute(TaskList taskList) {
        String name = findDefaultArgument(),
                from = findArgument("from"),
                to = findArgument("to");

        // Validate arguments
        if (name == null) {
            handleMissingArgument(getCommand(), "name");
            return;
        }
        if (from == null) {
            handleMissingArgument(getCommand(), "from");
            return;
        }
        if (to == null) {
            handleMissingArgument(getCommand(), "to");
            return;
        }

        // Perform behaviour
        taskList.addEvent(name, from, to);
        handleAddSuccess(taskList);
    }
}
