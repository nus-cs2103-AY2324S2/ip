package duke;

/**
 * Enumerates the types of commands that can be used in the Duke application.
 * Provides a method to convert a string command into a corresponding
 * CommandType.
 */
public enum CommandType {
    TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE, LIST, FIND, INVALID, INVALID_NUM, INVALID_FORMAT;

    /**
     * Converts a string to a CommandType.
     * If the command is not recognized, returns INVALID.
     *
     * @param command The command string to convert.
     * @return The corresponding CommandType or INVALID if not recognized.
     */
    public static CommandType fromString(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }
}
