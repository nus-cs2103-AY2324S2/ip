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
 * This encapsulates behaviour of deleting a {@link Task} from the {@link TaskList}.
 */
public final class DeleteAction extends IndexableAction {
    /** The {@link Command} for deleting a {@link Task}. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("delete", "index", IntegerStringValue.class)
    );

    /**
     * Constructor for this delete action.
     *
     * @param arguments The {@link Argument}(s) supplied with the {@link Command}.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public DeleteAction(SuppliedArgument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Deletes the {@link Task} from the {@link TaskList}.
     *
     * @param taskList The {@link TaskList} that is used with the {@link ChatBot}.
     * @throws InvalidArgumentValueException If the action fails certain validation checks due to invalid input.
     */
    @Override
    public void execute(TaskList taskList) throws InvalidArgumentValueException {
        // Perform behaviour
        Task deletedTask = performIndexingAction(taskList::deleteTask);

        PrintFormatter.addToMessageQueue(
                "Noted. I've removed this task:",
                "    " + deletedTask
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
