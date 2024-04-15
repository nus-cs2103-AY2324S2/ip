package chatbot.action;

import chatbot.ChatBot;
import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.action.util.SuppliedArgument;
import chatbot.print.PrintFormatter;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.value.StringValue;

/**
 * This encapsulates the finding of a {@link Task} by name,
 * using a regex pattern.
 *
 * @author Titus Chew
 */
public class FindAction extends Action {
    /** The {@link Command} for finding a {@link Task}. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("find", "name", StringValue.class)
    );

    /**
     * Constructor for this find action.
     *
     * @param arguments The {@link Argument}(s) supplied with the {@link Command}.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public FindAction(SuppliedArgument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Finds matching {@link Task}(s) from the {@link TaskList}.
     *
     * @param taskList The {@link TaskList} that is used with the {@link ChatBot}.
     */
    @Override
    public void execute(TaskList taskList) {
        String pattern = findDefaultArgument().toString();

        // Perform behaviour
        int[] matchingSortedTaskIndices = taskList.findMatchingTaskIndices(pattern);
        PrintFormatter.addToMessageQueue(
                "Here are the matching tasks in your list: ",
                taskList.toString(matchingSortedTaskIndices)
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
