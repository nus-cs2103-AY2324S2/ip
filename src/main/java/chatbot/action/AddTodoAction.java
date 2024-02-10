package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.task.ToDo;
import chatbot.ui.PrintFormatter;

/**
 * This encapsulates the behaviour of adding a {@link ToDo}.
 *
 * @author Titus Chew
 */
public final class AddTodoAction extends Action {
    /** The {@link Command} for adding a {@link ToDo}. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("todo", "name")
    );

    /**
     * Constructor for this add to-do action.
     *
     * @param arguments the {@link Argument}(s) supplied with the {@link Command}
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public AddTodoAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Add a {@link ToDo} to the user's list.
     *
     * @param taskList the {@link TaskList} to modify
     * @return the success message from performing the action
     */
    @Override
    public String execute(TaskList taskList) {
        String name = findDefaultArgument().toString();

        // Perform behaviour
        Task task = taskList.addTodo(name);
        return PrintFormatter.formatMessages(
                "Got it. I've added this to-do:",
                "    " + task,
                taskList.getSizeMessage()
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
