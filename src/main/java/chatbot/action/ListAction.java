package chatbot.action;

import chatbot.ChatBot;
import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

/**
 * This encapsulates the behaviour when listing the {@link Task}.
 *
 * @author Titus Chew
 */
public final class ListAction extends Action {
    /** The {@link Command} for listing stored {@link Task}(s). */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("list")
    );

    /**
     * Constructor for this list action.
     *
     * @param arguments the {@link Argument}(s) supplied with the {@link Command}
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public ListAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Prints the user's list.
     *
     * @param taskList the {@link TaskList} that is used with the {@link ChatBot}
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

    public static String getName() {
        return COMMAND.getName();
    }
}
