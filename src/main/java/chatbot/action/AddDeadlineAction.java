package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.ui.Printer;
import chatbot.task.Deadline;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.value.DateStringValue;

/**
 * This encapsulates the behaviour of adding a {@link Deadline}.
 *
 * @author Titus Chew
 */
public final class AddDeadlineAction extends Action {
    /** The {@link Command} for adding a {@link Deadline}. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("deadline", "name"),
            new ExpectedArgument("by", "by_date")
    );

    /**
     * Constructor for this add deadline action.
     *
     * @param arguments the {@link Argument}(s) supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public AddDeadlineAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Add a {@link Deadline} to the user's list.
     *
     * @param taskList the {@link TaskList} to modify
     */
    @Override
    public void execute(TaskList taskList) {
        String name = findDefaultArgument().toString();
        DateStringValue by = DateStringValue.of(findArgument("by"));

        // Perform behaviour
        Task task = taskList.addDeadline(name, by);
        Printer.printMessages(
                "Got it. I've added this deadline:",
                "    " + task,
                taskList.getSizeMessage()
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
