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
 * This encapsulates the behaviour of marking a {@link Task} as done.
 *
 * @author Titus Chew
 */
public final class MarkAction extends Action {
    /** The command for marking a {@link Task} as done. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("mark", "index")
    );

    /**
     * Constructor for this mark action.
     *
     * @param arguments the {@link Argument}(s) supplied with the {@link Command}
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public MarkAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Marks and prints the task.
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
            task = taskList.markTask(index - 1);
        } catch (OutOfBoundsException e) {
            throw new InvalidArgumentValueException(
                    getCommand(),
                    "index",
                    e.getMessage()
            );
        }

        return PrintFormatter.formatMessages(
                "Nice! I've marked this task as done:",
                "    " + task
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
