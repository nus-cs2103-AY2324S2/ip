package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.exception.UnrecognizedCommandException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.ExpectedArgument;
import chatbot.action.util.SuppliedArgument;
import chatbot.print.PrintFormatter;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.value.StringValue;

/**
 * An Action encapsulates the behaviour of a {@link Command} and it's {@link Argument}(s).
 * <li>An action may be invalid.
 * <li>An action can be executed, with validation checks.
 * <li><code>ModifyAction <: Action</code>
 * <li><code>IndexableAction <: ModifyAction</code>
 *
 * @author Titus Chew
 */
public abstract class Action {
    /** Stores the {@link Command} of this */
    private final Command command;

    /** Stores the supplied {@link Argument}(s) of this */
    private final Argument[] suppliedArguments;

    /**
     * Constructor for this action, which validates that it's {@link Argument}(s) are valid.
     *
     * @param command The {@link Command} associated with this action.
     * @param suppliedArguments The {@link Argument}(s) supplied with the command.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    Action(Command command, SuppliedArgument... suppliedArguments) throws ActionException {
        this.command = command;
        this.suppliedArguments = suppliedArguments;
        command.validateSuppliedArguments(suppliedArguments);
        command.castSuppliedArgumentsValues(suppliedArguments);
    }

    /**
     * Factory method to create an action instance.
     *
     * @param command The {@link Command} as a string.
     * @param parsedArguments The {@link Argument}(s), parsed from the input.
     * @return The action instance.
     * @throws ActionException If the {@link Command} or {@link Argument}(s) are not one of the expected values.
     */
    public static Action of(String command, SuppliedArgument[] parsedArguments) throws ActionException {
        if (command.equals(ByeAction.getName())) {
            return new ByeAction(parsedArguments);
        } else if (command.equals(ListAction.getName())) {
            return new ListAction(parsedArguments);
        } else if (command.equals(MarkAction.getName())) {
            return new MarkAction(parsedArguments);
        } else if (command.equals(UnmarkAction.getName())) {
            return new UnmarkAction(parsedArguments);
        } else if (command.equals(AddTodoAction.getName())) {
            return new AddTodoAction(parsedArguments);
        } else if (command.equals(AddDeadlineAction.getName())) {
            return new AddDeadlineAction(parsedArguments);
        } else if (command.equals(AddEventAction.getName())) {
            return new AddEventAction(parsedArguments);
        } else if (command.equals(DeleteAction.getName())) {
            return new DeleteAction(parsedArguments);
        } else if (command.equals(FindAction.getName())) {
            return new FindAction(parsedArguments);
        } else if (command.equals(UndoAction.getName())) {
            return new UndoAction(parsedArguments);
        } else {
            throw new UnrecognizedCommandException(command);
        }
    }

    /**
     * Gets the {@link Command} of this action.
     *
     * @return The {@link Command} associated with this action.
     */
    final Command getCommand() {
        return command;
    }

    /**
     * Finds the value of this action's {@link Argument} by the {@link Argument} name.
     *
     * @param <T> The same type of the {@link StringValue} that the corresponding {@link ExpectedArgument} specified.
     * @param name The non-null name of the {@link Argument} to find.
     * @return The value of the {@link Argument} with that name, or null if not found.
     */
    final <T extends StringValue> T findArgument(String name) {
        for (Argument arg : suppliedArguments) {
            if (arg.hasSameArgumentName(name)) {
                // It is safe to cast to T as the argument value has already been
                // validated and cast to the appropriate type in the constructor.
                @SuppressWarnings("unchecked")
                T tmp = (T) arg.getValue();
                return tmp;
            }
        }

        // when the argument of that name does not exist, it is an optional argument that is not present
        return null;
    }

    /**
     * Finds the default {@link Argument} of this action.
     *
     * @param <T> The same type of the {@link StringValue} that the corresponding {@link ExpectedArgument} specified.
     * @return The value of the default {@link Argument}.
     */
    final <T extends StringValue> T findDefaultArgument() {
        return findArgument(command.getName());
    }

    /**
     * Executes this action, which performs the behaviour associated with the {@link Command}.
     * <p>
     * This may modify the state of stored {@link Task}(s), and may write to the {@link PrintFormatter}.
     * <p>
     * If no error is thrown, the execution is successful.
     *
     * @param taskList The {@link TaskList} that is used with the chatbot.
     * @throws ActionException If the action fails certain validation checks due to invalid input.
     */
    public abstract void execute(TaskList taskList) throws ActionException;
}
