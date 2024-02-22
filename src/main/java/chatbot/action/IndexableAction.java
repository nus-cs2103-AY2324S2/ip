package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.exception.InvalidArgumentValueException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.SuppliedArgument;
import chatbot.exception.ThrowableFunction;
import chatbot.task.Task;
import chatbot.task.exception.OutOfBoundsException;
import chatbot.value.IntegerStringValue;
import chatbot.value.exception.InvalidValueTypeException;

/**
 * This represents an abstract {@link Action} that takes an index as it's default argument.
 * <p>
 * If an action takes an index, it must be an action that modifies a task or a task list.
 *
 * @author Titus Chew
 */
public abstract class IndexableAction extends ModifyAction {
    /**
     * Constructor for this indexable action.
     *
     * @param command The {@link Command} associated with this action.
     * @param suppliedArguments The {@link Argument}(s) supplied with the command.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    IndexableAction(Command command, SuppliedArgument[] suppliedArguments) throws ActionException {
        super(command, suppliedArguments);
    }

    /**
     * Gets the 1-indexed index from the default argument.
     *
     * @return The 1-indexed index from the default argument.
     */
    private int getIndex() {
        try {
            // minus one, as we pass in 1-indexed values from the command line
            IntegerStringValue index = findDefaultArgument();
            return index.tryGetIntegerValue() - 1;
        } catch (InvalidValueTypeException e) {
            throw new AssertionError("The value should have already been validated as an integer!");
        }
    }

    /**
     * Performs the indexing action on a task.
     *
     * @param taskIndexFunction A {@link ThrowableFunction} that takes in an index in the task list,
     *                          and returns the task which the action is performed on.
     * @return The task that {@code taskIndexFunction} returns.
     * @throws InvalidArgumentValueException If the action fails certain validation checks due to invalid input.
     */
    Task performIndexingAction(
            ThrowableFunction<Integer, Task, OutOfBoundsException> taskIndexFunction)
            throws InvalidArgumentValueException {
        try {
            return taskIndexFunction.apply(getIndex());
        } catch (OutOfBoundsException e) {
            throw new InvalidArgumentValueException(
                    getCommand(),
                    "index",
                    e.getMessage()
            );
        }
    }
}
