package alpa.commands;

/**
 * Represents the types of commands in the application.
 */
public enum CommandType {
    BYE, LIST, MARK, UNMARK, FIND, TODO, DEADLINE, EVENT, DELETE, INVALID, HELP;

    /**
     * Converts a string representation of a command into the corresponding CommandType.
     *
     * @param commandString the string representation of the command
     * @return the corresponding CommandType, or INVALID if the commandString is not a valid command
     */
    public static CommandType fromString(String commandString) {
        try {
            return CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }
}
