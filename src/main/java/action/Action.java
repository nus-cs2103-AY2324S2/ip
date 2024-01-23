package action;

import print.Printer;
import task.TaskList;

/**
 * An Action encapsulates the behaviour of a command and it's arguments.
 * An action may be invalid.
 * An action can be executed, with validation checks.
 *
 * @author Titus Chew
 */
public abstract class Action {
    private final Command command;
    private final Argument[] arguments;

    /**
     * Constructor for an Action.
     * @param command The command associated with the Action.
     * @param arguments The arguments supplied to the Command.
     */
    public Action(Command command, Argument... arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Gets the command of the action.
     * @return The command associated with the action
     */
    final Command getCommand() {
        return command;
    }

    /**
     * Finds the value of the argument by name.
     * @param name The non-null name of the argument to find.
     * @return The value of the argument with that name, or null if not found.
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
     * Finds the default argument of the action.
     * @return The value of the default argument.
     */
    final String findDefaultArgument() {
        return findArgument(command.name);
    }

    /**
     * Validation check that handles missing arguments in a command.
     */
    void handleMissingArgument(Command command, String missingArg) {
        Printer.printMessages(
                "OOPS!!! The argument <" + missingArg + "> of " + command.name + " must be present!",
                "    Usage: `" + command.usageHint + "`"
        );
    }

    /**
     * Prints the message when a task is added.
     */
    void handleAddSuccess(TaskList taskList) {
        Printer.printMessages(
                "Got it. I've added this task:",
                "    " + taskList.getNewestTask(),
                "Now you have " + taskList.size() + " task(s) in the list."
        );
    }

    /**
     * Executes the command, which may modify the state of stored tasks,
     * and may print to the console.
     * @param taskList The taskList that is used with the ChatBot.
     */
    public abstract void execute(TaskList taskList);
}
