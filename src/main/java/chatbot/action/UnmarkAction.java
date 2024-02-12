package chatbot.action;

import chatbot.ChatBot;
import chatbot.action.exception.ActionException;
import chatbot.action.exception.InvalidArgumentValueException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.PrintFormatter;

/**
 * This encapsulates the behaviour of marking a {@link Task} as not done.
 *
 * @author Titus Chew
 */
public final class UnmarkAction extends IndexableAction {
    /** The {@link Command} for marking a {@link Task} as not done. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("unmark", "index")
    );

    /**
     * Constructor for this unmark action.
     *
     * @param arguments The {@link Argument}(s) supplied with the {@link Command}.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public UnmarkAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Unmarks the {@link Task}.
     *
     * @param taskList The {@link TaskList} that is used with the {@link ChatBot}.
     * @return The success message from performing the action.
     * @throws InvalidArgumentValueException If the action fails certain validation checks due to invalid input.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidArgumentValueException {
        // Perform behaviour
        Task unmarkedTask = performIndexingAction(taskList::unmarkTask);

        return PrintFormatter.formatMessages(
                "Ok, I've marked this task as not done yet:",
                "    " + unmarkedTask
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
