package action;

import action.exception.ActionException;
import print.Printer;
import task.TaskList;

/**
 * ByeAction encapsulates the behaviour when ending the chat.
 *
 * @author Titus Chew
 */
public class ByeAction extends Action {
    /**
     * Constructor for this bye action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public ByeAction(Argument[] arguments) throws ActionException {
        super(Command.BYE, arguments);
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
}
