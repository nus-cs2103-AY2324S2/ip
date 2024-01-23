package action;

import action.exception.ActionException;
import action.exception.InvalidArgumentValueException;
import action.util.Argument;
import action.util.Command;
import print.Printer;
import task.Task;
import task.TaskList;

/**
 * UnmarkAction encapsulates the behaviour of marking a task as not done.
 *
 * @author Titus Chew
 */
public class UnmarkAction extends Action {
    /**
     * Constructor for this unmark action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public UnmarkAction(Argument[] arguments) throws ActionException {
        super(Command.UNMARK, arguments);
    }

    /**
     * Unmarks and prints the task.
     *
     * @param taskList the taskList that is used with the chatbot
     * @throws ActionException If the action fails certain validation checks due to invalid input.
     */
    @Override
    public void execute(TaskList taskList) throws ActionException {
        String indexString = findDefaultArgument();

        // Validate indexString as an integer
        int index;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentValueException(
                    getCommand(),
                    "index",
                    "<index> is not an integer."
            );
        }

        if (taskList.isEmpty()) {
            throw new InvalidArgumentValueException(
                    getCommand(),
                    "index",
                    "<index> is out of range as there are no tasks in your list."
            );
        }

        // Validate that indexString is in the range
        if (!taskList.isValidIndex(index)) {
            throw new InvalidArgumentValueException(
                    getCommand(),
                    "index",
                    "<index> is out of range. <index> must be between 1 and " + taskList.size() + "."
            );
        }

        // Perform behaviour
        Task task = taskList.unmarkTask(index);
        Printer.printMessages(
                "Ok, I've marked this task as not done yet:",
                "    " + task
        );
    }
}
