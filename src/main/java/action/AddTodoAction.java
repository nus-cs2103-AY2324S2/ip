package action;

import task.TaskList;

/**
 * AddTodoCommand encapsulates the behaviour of adding a to-do.
 *
 * @author Titus Chew
 */
public class AddTodoAction extends Action {
    /**
     * Constructor for this add to-do action.
     *
     * @param arguments the arguments supplied with the command
     */
    public AddTodoAction(Argument[] arguments) {
        super(Command.ADD_TODO, arguments);
    }

    /**
     * Add a to-do to the user's list.
     *
     * @param taskList the taskList to modify
     */
    @Override
    public void execute(TaskList taskList) {
        String name = findDefaultArgument();

        // Validate arguments
        if (name == null) {
            handleMissingArgument(getCommand(), "name");
            return;
        }

        // Perform behaviour
        taskList.addTodo(name);
        handleAddSuccess(taskList);
    }
}
