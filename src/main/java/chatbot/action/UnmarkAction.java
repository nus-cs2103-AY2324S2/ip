package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.exception.InvalidArgumentValueException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.io.ui.Printer;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.task.exception.OutOfBoundsException;
import chatbot.value.IntegerStringValue;
import chatbot.value.exception.InvalidValueTypeException;

/**
 * This encapsulates the behaviour of marking a {@link Task} as not done.
 *
 * @author Titus Chew
 */
public final class UnmarkAction extends Action {
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
        // Validate indexString as an integer
        int index;
        try {
            index = IntegerStringValue
                    .of(findDefaultArgument())
                    .tryGetIntegerValue();
        } catch (InvalidValueTypeException e) {
            throw new InvalidArgumentValueException(
                    getCommand(),
                    "index",
                    e.getMessage()
            );
        }

        // Perform behaviour
        Task task;
        try {
            task = taskList.unmarkTask(index - 1);
        } catch (OutOfBoundsException e) {
            throw new InvalidArgumentValueException(
                    getCommand(),
                    "index",
                    e.getMessage()
            );
        }

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
