package action;

import action.exception.ActionException;
import action.exception.UnrecognizedArgumentException;
import action.exception.UnrecognizedCommandException;
import action.util.Argument;
import action.util.Command;
import task.TaskList;

/**
 * An Action encapsulates the behaviour of a {@link Command} and it's {@link Argument}(s).
 * <ul>
 * <li>An action may be invalid.
 * <li>An action can be executed, with validation checks.
 *
 * @author Titus Chew
 */
public abstract class Action {
    private final Command command;
    private final Argument[] arguments;

    /**
     * Constructor for this action, which validates that it's arguments are valid.
     *
     * @param command the command associated with this action
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public Action(Command command, Argument... arguments) throws ActionException {
        this.command = command;
        this.arguments = arguments;
        validateExpectedArguments();
        command.validateSuppliedArguments(arguments);
    }

    /**
     * Factory method to create an action instance.
     *
     * @param command the command as a string
     * @param parsedArguments the arguments, parsed from the input
     * @return the action instance
     * @throws ActionException If the command or arguments are not one of the expected values.
     */
    public static Action of(String command, Argument[] parsedArguments) throws ActionException {
        if (command.equals(Command.BYE.getName())) {
            return new ByeAction(parsedArguments);
        } else if (command.equals(Command.LIST.getName())) {
            return new ListAction(parsedArguments);
        } else if (command.equals(Command.MARK.getName())) {
            return new MarkAction(parsedArguments);
        } else if (command.equals(Command.UNMARK.getName())) {
            return new UnmarkAction(parsedArguments);
        } else if (command.equals(Command.ADD_TODO.getName())) {
            return new AddTodoAction(parsedArguments);
        } else if (command.equals(Command.ADD_DEADLINE.getName())) {
            return new AddDeadlineAction(parsedArguments);
        } else if (command.equals(Command.ADD_EVENT.getName())) {
            return new AddEventAction(parsedArguments);
        } else if (command.equals(Command.DELETE.getName())) {
            return new DeleteAction(parsedArguments);
        } else {
            throw new UnrecognizedCommandException(command);
        }
    }

    /**
     * Gets the command of this action.
     *
     * @return the command associated with this action
     */
    final Command getCommand() {
        return command;
    }

    /**
     * Validates the argument names.
     *
     * @throws UnrecognizedArgumentException If an argument is unrecognizable.
     */
    private void validateExpectedArguments() throws UnrecognizedArgumentException {
        for (Argument suppliedArg : arguments) {
            if (!command.hasArgumentName(suppliedArg)) {
                throw new UnrecognizedArgumentException(command, suppliedArg);
            }
        }
    }

    /**
     * Finds the value of this action's argument by the argument name.
     *
     * @param name the non-null name of the argument to find
     * @return the value of the argument with that name, or null if not found
     */
    final String findArgument(String name) {
        for (Argument arg : arguments) {
            if (arg.getName().equals(name)) {
                return arg.getValue();
            }
        }
        // null represents that the argument of that name does not exist.
        return null;
    }

    /**
     * Finds the default argument of this action.
     *
     * @return the value of the default argument
     */
    final String findDefaultArgument() {
        return findArgument(command.getName());
    }

    /**
     * Executes this action, which may modify the state of stored tasks,
     * and may print to the console.
     *
     * @param taskList the taskList that is used with the chatbot
     * @throws ActionException If the action fails certain validation checks due to invalid input.
     */
    public abstract void execute(TaskList taskList) throws ActionException;
}
