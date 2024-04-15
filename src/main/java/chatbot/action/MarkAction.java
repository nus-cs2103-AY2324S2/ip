package chatbot.action;

import chatbot.ChatBot;
import chatbot.action.exception.ActionException;
import chatbot.action.exception.InvalidArgumentValueException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.action.util.SuppliedArgument;
import chatbot.print.PrintFormatter;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.value.IntegerStringValue;

/**
 * This encapsulates the behaviour of marking a {@link Task} as done.
 *
 * @author Titus Chew
 */
public final class MarkAction extends IndexableAction {
    /** The command for marking a {@link Task} as done. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("mark", "index", IntegerStringValue.class)
    );

    /**
     * Constructor for this mark action.
     *
     * @param arguments The {@link Argument}(s) supplied with the {@link Command}.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public MarkAction(SuppliedArgument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Marks the task.
     *
     * @param taskList The {@link TaskList} that is used with the {@link ChatBot}.
     * @throws InvalidArgumentValueException If the action fails certain validation checks due to invalid input.
     */
    @Override
    public void execute(TaskList taskList) throws InvalidArgumentValueException {
        // Perform behaviour
        Task markedTask = performIndexingAction(taskList::markTask);

        PrintFormatter.addToMessageQueue(
                "Nice! I've marked this task as done:",
                "    " + markedTask
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
