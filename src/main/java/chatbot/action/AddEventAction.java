package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.io.ui.Printer;
import chatbot.task.Task;
import chatbot.task.TaskList;

/**
 * AddEventCommand encapsulates the behaviour of adding an event.
 *
 * @author Titus Chew
 */
public class AddEventAction extends Action {
    /**
     * Constructor for this add event action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public AddEventAction(Argument[] arguments) throws ActionException {
        super(Command.ADD_EVENT, arguments);
    }

    /**
     * Add an event to the task list.
     *
     * @param taskList the taskList to modify
     */
    @Override
    public void execute(TaskList taskList) {
        String name = findDefaultArgument(),
                from = findArgument("from"),
                to = findArgument("to");

        // Perform behaviour
        Task task = taskList.addEvent(name, from, to);
        Printer.printMessages(
                "Got it. I've added this event:",
                "    " + task,
                "Now you have " + taskList.size() + " task(s) in the list."
        );
    }
}
