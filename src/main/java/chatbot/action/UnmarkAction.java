package chatbot.action;

import chatbot.ChatBot;
import chatbot.action.exception.ActionException;
import chatbot.action.exception.InvalidArgumentValueException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.task.exception.OutOfBoundsException;
import chatbot.ui.PrintFormatter;
import chatbot.value.IntegerStringValue;
import chatbot.value.exception.InvalidValueTypeException;

/**
 * This encapsulates the behaviour of marking a {@link Task} as not done.
 *
 * @author Titus Chew
 */
public final class UnmarkAction extends Action {
    /** The {@link Command} for marking a {@link Task} as not done. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("unmark", "index")
    );

    /**
     * Constructor for this unmark action.
     *
     * @param arguments the {@link Argument}(s) supplied with the {@link Command}
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public UnmarkAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Unmarks and prints the {@link Task}.
     *
     * @param taskList the {@link TaskList} that is used with the {@link ChatBot}
     * @return the success message from performing the action
     * @throws InvalidArgumentValueException If the action fails certain validation checks due to invalid input.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidArgumentValueException {
        // Validate indexString as an integer
        int index;
        try {
            index = new IntegerStringValue(findDefaultArgument())
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

        return PrintFormatter.formatMessages(
                "Ok, I've marked this task as not done yet:",
                "    " + task
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
