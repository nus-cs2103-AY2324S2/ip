package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.PrintFormatter;

/**
 * This encapsulates the finding of a {@link Task} by name,
 * using a regex pattern.
 *
 * @author Titus Chew
 */
public class FindAction extends Action {
    /** The {@link Command} for finding a {@link Task}. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("find", "name")
    );

    /**
     * Constructor for this find action.
     *
     * @param arguments the {@link Argument}(s) supplied with the {@link Command}
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public FindAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Finds matching {@link Task}(s) from the {@link TaskList}.
     *
     * @param taskList the {@link TaskList} that is used with the {@link chatbot.ChatBot}
     * @return the success message from performing the action
     */
    @Override
    public String execute(TaskList taskList) {
        String pattern = findDefaultArgument().toString();

        // Perform behaviour
        int[] matchingTaskIndices = taskList.findMatchingTasks(pattern);
        return PrintFormatter.formatMessages(
                "Here are the matching tasks in your list: ",
                taskList.toString(matchingTaskIndices)
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
