package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.io.ui.Printer;
import chatbot.task.TaskList;

/**
 * ByeAction encapsulates the behaviour when ending the chat.
 *
 * @author Titus Chew
 */
public final class ByeAction extends Action {
    /**
     * The command for ending the chat
     */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("bye")
    );

    /**
     * Constructor for this bye action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public ByeAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Greets the user when exiting the application.
     *
     * @param taskList the taskList that is used with the chatbot
     */
    @Override
    public void execute(TaskList taskList) {
        // Perform behaviour
        Printer.printMessages("Bye! Hope to see you again soon!");
    }

    /**
     * Gets the name of the {@link Command}.
     */
    public static String getName() {
        return COMMAND.getName();
    }
}
