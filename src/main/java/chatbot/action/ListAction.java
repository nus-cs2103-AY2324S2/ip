package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.task.TaskList;
import chatbot.io.ui.Printer;

/**
 * ListAction encapsulates the behaviour when listing the tasks.
 *
 * @author Titus Chew
 */
public class ListAction extends Action{
    /**
     * Constructor for this list action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public ListAction(Argument[] arguments) throws ActionException {
        super(Command.LIST, arguments);
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
}
