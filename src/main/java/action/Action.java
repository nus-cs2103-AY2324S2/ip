package action;

import print.Printer;
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
     */
    public Action(Command command, Argument... arguments) {
        this.command = command;
        this.arguments = arguments;
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
     * Validation handler when there are missing arguments in a command.
     *
     * @param command the command
     * @param missingArg the missing argument
     */
    void handleMissingArgument(Command command, String missingArg) {
        Printer.printMessages(
                "OOPS!!! The argument <" + missingArg + "> of " + command.name + " must be present!",
                "    Usage: `" + command.usageHint + "`"
        );
    }

    /**
     * Prints the message when a task is added by this action
     *
     * @param taskList the taskList that is used with the chatbot
     */
    void handleAddSuccess(TaskList taskList) {
        Printer.printMessages(
                "Got it. I've added this task:",
                "    " + taskList.getNewestTask(),
                "Now you have " + taskList.size() + " task(s) in the list."
        );
    }

    /**
     * Executes this action, which may modify the state of stored tasks,
     * and may print to the console.
     *
     * @param taskList the taskList that is used with the chatbot
     */
    public abstract void execute(TaskList taskList);
}
