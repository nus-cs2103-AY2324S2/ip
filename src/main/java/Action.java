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
     * @return the command associated with the action
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Finds the value of the argument by name.
     * @return the value of the argument with that name
     */
    public String findArgument(String name) {
        for (Argument arg : arguments) {
            if (arg.hasMatchingName(name)) {
                return arg.getValue();
            }
        }
        // null represents that the argument of that name does not exist.
        return null;
    }

    /**
     * Finds the default argument of the action
     * @return the value of the default argument
     */
    public String findDefaultArgument() {
        return findArgument(null);
    }
}
