package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.action.util.SuppliedArgument;
import chatbot.print.PrintFormatter;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.value.DateStringValue;
import chatbot.value.StringValue;

/**
 * This encapsulates the behaviour of adding an {@link Event}.
 *
 * @author Titus Chew
 */
public final class AddEventAction extends ModifyAction {
    /**
     * The {@link Command} for adding an {@link Event}.
     */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("event", "name", StringValue.class),
            new ExpectedArgument("from", "start_date", DateStringValue.class),
            new ExpectedArgument("to", "end_date", DateStringValue.class)
    );

    /**
     * Constructor for this add event action.
     *
     * @param arguments The {@link Argument}(s) supplied with the {@link Command}.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public AddEventAction(SuppliedArgument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Adds an {@link Event} to the {@link TaskList}.
     *
     * @param taskList The {@link TaskList} to modify.
     */
    @Override
    public void execute(TaskList taskList) {
        String name = findDefaultArgument().toString();
        DateStringValue from = findArgument("from");
        DateStringValue to = findArgument("to");

        // Perform behaviour
        Task task = taskList.addEvent(name, from, to);
        PrintFormatter.addToMessageQueue(
                "Got it. I've added this event:",
                "    " + task,
                taskList.getSizeMessage()
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
