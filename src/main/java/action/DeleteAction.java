package action;

import action.exception.ActionException;
import action.exception.InvalidArgumentValueException;
import action.exception.MissingArgumentValueException;
import action.util.Argument;
import action.util.Command;
import print.Printer;
import task.Task;
import task.TaskList;

/**
 * DeleteAction encapsulates behaviour of deleting a task from the task list.
 */
public class DeleteAction extends Action {
    /**
     * Constructor for this delete action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public DeleteAction(Argument[] arguments) throws ActionException {
        super(Command.DELETE, arguments);
    }

    /**
     * Deletes the task from the task list.
     *
     * @param taskList the taskList that is used with the chatbot
     * @throws ActionException If the action fails certain validation checks due to invalid input.
     */
    @Override
    public void execute(TaskList taskList) throws ActionException {
        String indexString = findDefaultArgument();

        // Validate arguments
        if (indexString == null) {
            throw new MissingArgumentValueException(getCommand(), "name");
        }

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
        Task task = taskList.deleteTask(index);
        Printer.printMessages(
                "Noted. I've removed this task:",
                "    " + task
        );
    }
}
