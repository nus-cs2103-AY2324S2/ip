package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.io.ui.Printer;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.value.DateStringValue;

/**
 * AddEventCommand encapsulates the behaviour of adding an event.
 *
 * @author Titus Chew
 */
public final class AddEventAction extends Action {
    /**
     * The command for adding an {@link Event}.
     */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("event", "name"),
            new ExpectedArgument("from", "start_date"),
            new ExpectedArgument("to", "end_date")
    );

    /**
     * Constructor for this add event action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public AddEventAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Add an event to the task list.
     *
     * @param taskList the taskList to modify
     */
    @Override
    public void execute(TaskList taskList) {
        String name = findDefaultArgument();
        DateStringValue from = new DateStringValue(findArgument("from")),
                to = new DateStringValue(findArgument("to"));

        // Perform behaviour
        Task task = taskList.addEvent(name, from, to);
        Printer.printMessages(
                "Got it. I've added this event:",
                "    " + task,
                "Now you have " + taskList.size() + " task(s) in the list."
        );
    }

    /**
     * Gets the name of the {@link Command}.
     */
    public static String getName() {
        return COMMAND.getName();
    }
}
