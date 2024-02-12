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
 * This encapsulates behaviour of deleting a {@link Task} from the {@link TaskList}.
 */
public final class DeleteAction extends IndexableAction {
    /** The {@link Command} for deleting a {@link Task}. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("delete", "index")
    );

    /**
     * Constructor for this delete action.
     *
     * @param arguments The {@link Argument}(s) supplied with the {@link Command}.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public DeleteAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Deletes the {@link Task} from the {@link TaskList}.
     *
     * @param taskList The {@link TaskList} that is used with the {@link ChatBot}.
     * @return The success message from performing the action.
     * @throws InvalidArgumentValueException If the action fails certain validation checks due to invalid input.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidArgumentValueException {
        // Perform behaviour
        Task deletedTask = performIndexingAction(taskList::deleteTask);

        return PrintFormatter.formatMessages(
                "Noted. I've removed this task:",
                "    " + deletedTask
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
