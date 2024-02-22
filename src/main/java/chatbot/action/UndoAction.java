package chatbot.action;

import java.util.Arrays;
import java.util.Optional;

import chatbot.ChatBot;
import chatbot.action.exception.ActionException;
import chatbot.action.exception.InvalidArgumentValueException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.action.util.OptionalArgument;
import chatbot.action.util.SuppliedArgument;
import chatbot.print.PrintFormatter;
import chatbot.storage.SaveState;
import chatbot.task.TaskList;
import chatbot.value.IntegerStringValue;

/**
 * This encapsulates the behaviour of undoing a {@link ModifyAction}.
 *
 * @author Titus Chew
 */
public class UndoAction extends Action {
    /** The {@link Command} for undoing a change. */
    private static final Command COMMAND = new Command(
            new ExpectedArgument("undo"),
            new OptionalArgument("by", "steps", IntegerStringValue.class)
    );
    /** The default number of steps to rollback. */
    private static final int DEFAULT_ROLLBACK_STEPS = 1;

    /**
     * Constructor for this undo action.
     *
     * @param arguments The {@link Argument}(s) supplied with the {@link Command}.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public UndoAction(SuppliedArgument[] arguments) throws ActionException {
        super(COMMAND, arguments);
    }

    /**
     * Undoes the previous change (if any).
     *
     * @param taskList The {@link TaskList} that is used with the {@link ChatBot}.
     * @throws InvalidArgumentValueException If steps is an invalid value.
     */
    @Override
    public void execute(TaskList taskList) throws InvalidArgumentValueException {
        // Perform behaviour
        String[] rolledBackCommands = SaveState.rollback(getNumberOfRollbackSteps());

        if (rolledBackCommands.length == 0) {
            PrintFormatter.addToMessageQueue("There is nothing to undo!");
            return;
        }

        taskList.restoreState(SaveState.queryCurrentState());
        String previousCommands = Arrays.stream(rolledBackCommands)
                .reduce("", (commandList, command) -> commandList + "\n    `" + command + "`")
                .trim();
        PrintFormatter.addToMessageQueue(
                "Ok, I've undone the previous change(s):",
                "    " + previousCommands,
                taskList.isEmpty()
                        ? "Your list is empty."
                        : "Here's your new list:",
                taskList.toString()
        );
    }

    /**
     * Gets the number of rollback steps to perform.
     *
     * @throws InvalidArgumentValueException If steps is an invalid value.
     */
    private int getNumberOfRollbackSteps() throws InvalidArgumentValueException {
        // Get optional argument
        IntegerStringValue by = findArgument("by");
        int stepsToRollbackBy = Optional
                .ofNullable(by)
                .map(i -> i.getIntegerValue(DEFAULT_ROLLBACK_STEPS))
                .orElse(DEFAULT_ROLLBACK_STEPS);

        // Validate
        if (stepsToRollbackBy < 1) {
            throw new InvalidArgumentValueException(
                    getCommand(),
                    "steps",
                    "Cannot rollback a non-positive number of steps!"
            );
        }

        return stepsToRollbackBy;
    }

    public static String getName() {
        return COMMAND.getName();
    }
}
