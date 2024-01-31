package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.exception.UnrecognizedCommandException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.value.StringValue;

/**
 * An Action encapsulates the behaviour of a {@link Command} and it's {@link Argument}(s).
 * <ul>
 * <li>An action may be invalid.
 * <li>An action can be executed, with validation checks.
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
     * @param command the {@link Command} associated with this action
     * @param suppliedArguments the {@link Argument}(s) supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    public Action(Command command, Argument... suppliedArguments) throws ActionException {
        this.command = command;
        this.suppliedArguments = suppliedArguments;
        command.validateSuppliedArguments(suppliedArguments);
    }

    /**
     * Factory method to create an action instance.
     *
     * @param command the {@link Command} as a string
     * @param parsedArguments the {@link Argument}(s), parsed from the input
     * @return the action instance
     * @throws ActionException If the {@link Command} or {@link Argument}(s) are not one of the expected values.
     */
    public static Action of(String command, Argument[] parsedArguments) throws ActionException {
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
        } else {
            throw new UnrecognizedCommandException(command);
        }
    }

    /**
     * Gets the {@link Command} of this action.
     *
     * @return the {@link Command} associated with this action
     */
    final Command getCommand() {
        return command;
    }

    /**
     * Finds the value of this action's {@link Argument} by the {@link Argument} name.
     *
     * @param name the non-null name of the {@link Argument} to find
     * @return the value of the {@link Argument} with that name, or null if not found
     */
    final StringValue findArgument(String name) {
        for (Argument arg : suppliedArguments) {
            if (arg.hasSameArgumentName(name)) {
                return arg.getValue();
            }
        }

        // this represents that the argument of that name does not exist,
        // which should not happen, since the argument has been validated.
        // so an empty StringValue is returned.
        return new StringValue("");
    }

    /**
     * Finds the default {@link Argument} of this action.
     *
     * @return the value of the default {@link Argument}
     */
    final StringValue findDefaultArgument() {
        return findArgument(command.getName());
    }

    /**
     * Executes this action, which may modify the state of stored {@link Task}(s),
     * and may print to the console.
     *
     * @param taskList the {@link TaskList} that is used with the chatbot
     * @throws ActionException If the action fails certain validation checks due to invalid input.
     */
    public abstract void execute(TaskList taskList) throws ActionException;
}
