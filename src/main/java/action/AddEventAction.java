package action;

import action.exception.ActionException;
import action.util.Argument;
import action.util.Command;
import print.Printer;
import task.Task;
import task.TaskList;

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
     * @throws ActionException If the action fails certain validation checks due to invalid input.
     */
    @Override
    public void execute(TaskList taskList) throws ActionException {
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
