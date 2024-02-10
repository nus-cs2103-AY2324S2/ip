package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.PrintFormatter;
import chatbot.value.DateStringValue;

/**
 * This encapsulates the behaviour of adding an {@link Event}.
 *
 * @author Titus Chew
 */
public final class AddEventAction extends Action {
    /**
     * The {@link Command} for adding an {@link Event}.
     */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("event", "name"),
            new ExpectedArgument("from", "start_date"),
            new ExpectedArgument("to", "end_date")
    );

    /**
     * Constructor for this add event action.
     *
     * @param arguments the {@link Argument}(s) supplied with the {@link Command}
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public AddEventAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Add an {@link Event} to the {@link TaskList}.
     *
     * @param taskList the {@link TaskList} to modify
     * @return the success message from performing the action
     */
    @Override
    public String execute(TaskList taskList) {
        String name = findDefaultArgument().toString();
        DateStringValue from = new DateStringValue(findArgument("from"));
        DateStringValue to = new DateStringValue(findArgument("to"));

        // Perform behaviour
        Task task = taskList.addEvent(name, from, to);
        return PrintFormatter.formatMessages(
                "Got it. I've added this event:",
                "    " + task,
                taskList.getSizeMessage()
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
