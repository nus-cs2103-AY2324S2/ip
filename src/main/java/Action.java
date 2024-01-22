/**
 * An Action encapsulates the ChatBot command and it's arguments.
 *
 * @author Titus Chew
 */
public class Action {
    private final Command command;
    private final Argument[] arguments;
    public Action(Command command, Argument... arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Gets the command of the action.
     * @return The command associated with the action
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Finds the value of the argument by name.
     * @param name The non-null name of the argument to find.
     * @return The value of the argument with that name, or null if not found.
     */
    public String findArgument(String name) {
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
    public String findDefaultArgument() {
        return findArgument(command.name);
    }
}
