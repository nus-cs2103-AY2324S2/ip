package chatbot.action;

import java.util.Arrays;

import chatbot.ChatBot;
import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.storage.SaveState;
import chatbot.task.TaskList;
import chatbot.ui.PrintFormatter;

/**
 * This encapsulates the behaviour of undoing a {@link ModifyAction}.
 *
 * @author Titus Chew
 */
public class UndoAction extends Action {
    /** The {@link Command} for undoing a change. */
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
        // currently rollback supports any number of rollbacks, but undo command can only roll back once
        String[] rolledBackCommands = SaveState.rollback(1);

        if (rolledBackCommands.length == 0) {
            PrintFormatter.addToFormatterQueue("There is nothing to undo!");
            return;
        }

        taskList.restoreState(SaveState.queryCurrentState());
        String previousCommands = Arrays.stream(rolledBackCommands)
                .reduce("", (commandList, command) -> commandList + "\n    `" + command + "`")
                .trim();
        PrintFormatter.addToFormatterQueue(
                "Ok, I've undone the previous change",
                "    " + previousCommands,
                taskList.isEmpty()
                        ? "Your list is empty."
                        : "Here's your new list:",
                taskList.toString()
        );
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
