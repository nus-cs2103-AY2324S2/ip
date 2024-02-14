package chatbot.action;

import chatbot.ChatBot;
import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.storage.SaveState;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.PrintFormatter;

/**
 * This encapsulates the behaviour of undoing a {@link ModifyAction}.
 *
 * @author Titus Chew
 */
public class UndoAction extends Action {
    /** The {@link Command} for marking a {@link Task} as not done. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("undo")
    );

    /**
     * Constructor for this undo action.
     *
     * @param arguments The {@link Argument}(s) supplied with the {@link Command}.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public UndoAction(Argument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Undo the previous change (if any).
     *
     * @param taskList The {@link TaskList} that is used with the {@link ChatBot}.
     */
    @Override
    public void execute(TaskList taskList) {
        // Perform behaviour
        taskList.restoreState(SaveState.rollback(1));

        PrintFormatter.addToFormatterQueue(
                "Ok, I've undone the previous change.",
                taskList.isEmpty()
                        ? "Your list is empty."
                        : "Here's your new list:\n" + taskList
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
