package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.io.ui.Printer;
import chatbot.task.Task;
import chatbot.task.TaskList;

/**
 * AddDeadlineCommand encapsulates the behaviour of adding a deadline.
 *
 * @author Titus Chew
 */
public class AddDeadlineAction extends Action {
    /**
     * Constructor for this add deadline action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public AddDeadlineAction(Argument[] arguments) throws ActionException {
        super(Command.ADD_DEADLINE, arguments);
    }

    /**
     * Add a deadline task to the user's list.
     *
     * @param taskList the taskList to modify
     */
    @Override
    public void execute(TaskList taskList) {
        String name = findDefaultArgument(),
                by = findArgument("by");

        // Perform behaviour
        Task task = taskList.addDeadline(name, by);
        Printer.printMessages(
                "Got it. I've added this deadline:",
                "    " + task,
                "Now you have " + taskList.size() + " task(s) in the list."
        );
    }
}
