package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.exception.InvalidArgumentValueException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.io.ui.Printer;
import chatbot.task.Task;
import chatbot.task.TaskList;

/**
 * This encapsulates the behaviour of marking a {@link Task} as not done.
 *
 * @author Titus Chew
 */
public class UnmarkAction extends Action {
    /**
     * The command for marking a {@link Task} as not done.
     */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("unmark", "index")
    );

    /**
     * Constructor for this unmark action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public UnmarkAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Unmarks and prints the task.
     *
     * @param taskList the taskList that is used with the chatbot
     * @throws InvalidArgumentValueException If the action fails certain validation checks due to invalid input.
     */
    @Override
    public void execute(TaskList taskList) throws InvalidArgumentValueException {
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

    /**
     * Gets the name of the {@link Command}.
     */
    public static String getName() {
        return COMMAND.getName();
    }
}
