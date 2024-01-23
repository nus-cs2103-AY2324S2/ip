package action;

import action.exception.ActionException;
import action.exception.MissingArgumentValueException;
import print.Printer;
import task.Task;
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
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public AddDeadlineAction(Argument[] arguments) throws ActionException {
        super(Command.ADD_DEADLINE, arguments);
    }

    /**
     * Add a deadline task to the user's list.
     *
     * @param taskList the taskList to modify
     * @throws ActionException If the action fails certain validation checks due to invalid input.
     */
    @Override
    public void execute(TaskList taskList) throws ActionException {
        String name = findDefaultArgument(),
                by = findArgument("by");

        // Validate arguments
        if (name == null) {
            throw new MissingArgumentValueException(getCommand(), "name");
        }
        if (by == null) {
            throw new MissingArgumentValueException(getCommand(), "by");
        }

        // Perform behaviour
        Task task = taskList.addDeadline(name, by);
        Printer.printMessages(
                "Got it. I've added this deadline:",
                "    " + task,
                "Now you have " + taskList.size() + " task(s) in the list."
        );
    }
}
