package chatbot.action;

import chatbot.ChatBot;
import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

/**
 * This encapsulates the behaviour when ending the chat.
 *
 * @author Titus Chew
 */
public final class ByeAction extends Action {
    /** The {@link Command} for ending the chat */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("bye")
    );

    /**
     * Constructor for this bye action.
     *
     * @param arguments the {@link Argument}(s) supplied with the {@link Command}
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public ByeAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Greets the user when exiting the application.
     *
     * @param taskList the {@link TaskList} that is used with the {@link ChatBot}
     */
    @Override
    public void execute(TaskList taskList) {
        // Perform behaviour
        Printer.printMessages("Bye! Hope to see you again soon!");
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
