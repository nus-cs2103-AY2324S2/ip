package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.action.util.SuppliedArgument;
import chatbot.print.PrintFormatter;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.task.ToDo;
import chatbot.value.StringValue;

/**
 * This encapsulates the behaviour of adding a {@link ToDo}.
 *
 * @author Titus Chew
 */
public final class AddTodoAction extends ModifyAction {
    /** The {@link Command} for adding a {@link ToDo}. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("todo", "name", StringValue.class)
    );

    /**
     * Constructor for this add to-do action.
     *
     * @param arguments The {@link Argument}(s) supplied with the {@link Command}.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public AddTodoAction(SuppliedArgument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Adds a {@link ToDo} to the user's list.
     *
     * @param taskList The {@link TaskList} to modify.
     */
    @Override
    public void execute(TaskList taskList) {
        String name = findDefaultArgument().toString();

        // Perform behaviour
        Task task = taskList.addTodo(name);
        PrintFormatter.addToMessageQueue(
                "Got it. I've added this to-do:",
                "    " + task,
                taskList.getSizeMessage()
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
