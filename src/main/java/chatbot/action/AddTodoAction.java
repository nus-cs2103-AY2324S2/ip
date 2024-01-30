package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.io.ui.Printer;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.task.ToDo;

/**
 * AddTodoCommand encapsulates the behaviour of adding a to-do.
 *
 * @author Titus Chew
 */
public final class AddTodoAction extends Action {
    /**
     * The command for adding a {@link ToDo}.
     */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("todo", "name")
    );

    /**
     * Constructor for this add to-do action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public AddTodoAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Add a to-do to the user's list.
     *
     * @param taskList the taskList to modify
     */
    @Override
    public void execute(TaskList taskList) {
        String name = findDefaultArgument().toString();

        // Perform behaviour
        Task task = taskList.addTodo(name);
        Printer.printMessages(
                "Got it. I've added this to-do:",
                "    " + task,
                taskList.getSizeMessage()
        );
    }

    /**
     * Gets the name of the {@link Command}.
     */
    public static String getName() {
        return COMMAND.getName();
    }
}
