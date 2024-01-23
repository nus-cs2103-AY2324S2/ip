package action;

import action.exception.ActionException;
import action.exception.UnrecognizedArgumentException;
import action.exception.MissingArgumentException;
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
     * Constructor for this action.
     *
     * @param command the command associated with this action
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments
     */
    public Action(Command command, Argument... arguments) throws ActionException {
        this.command = command;
        this.arguments = arguments;
        validateExpectedArguments();
        validateSuppliedArguments();
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
            boolean isRecognized = false;
            for (Argument expectedArg : command.arguments) {
                if (expectedArg.name.equals(suppliedArg.name)) {
                    isRecognized = true;
                    break;
                }
            }

            if (!isRecognized) {
                throw new UnrecognizedArgumentException(suppliedArg.name, command);
            }
        }
    }

    /**
     * Validates the argument names.
     *
     * @throws MissingArgumentException If an argument is missing.
     */
    private void validateSuppliedArguments() throws MissingArgumentException {
        for (Argument expectedArg : command.arguments) {
            boolean isRecognized = false;
            for (Argument suppliedArg : arguments) {
                if (expectedArg.name.equals(suppliedArg.name)) {
                    isRecognized = true;
                    break;
                }
            }

            if (!isRecognized) {
                throw new MissingArgumentException(command, expectedArg.name);
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
            if (arg.name.equals(name)) {
                return arg.value;
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
        return findArgument(command.name);
    }

    /**
     * Executes this action, which may modify the state of stored tasks,
     * and may print to the console.
     *
     * @param taskList the taskList that is used with the chatbot
     * @throws ActionException If the action fails certain validation checks due to invalid input
     */
    public abstract void execute(TaskList taskList) throws ActionException;
}
