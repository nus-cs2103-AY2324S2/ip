package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.task.TaskList;
import chatbot.io.ui.Printer;

/**
 * ListAction encapsulates the behaviour when listing the tasks.
 *
 * @author Titus Chew
 */
public final class ListAction extends Action{
    /**
     * The command for listing stored tasks.
     */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("list")
    );

    /**
     * Constructor for this list action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public ListAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Prints the user's list.
     *
     * @param taskList the taskList that is used with the chatbot
     */
    @Override
    public void execute(TaskList taskList) {
        if (taskList.isEmpty()) {
            Printer.printMessages(
                    "Your list is empty."
            );
        } else {
            Printer.printMessages(
                    "Here are the tasks in your list:",
                    taskList.toString()
            );
        }
    }

    /**
     * Gets the name of the {@link Command}.
     */
    public static String getName() {
        return COMMAND.getName();
    }
}
