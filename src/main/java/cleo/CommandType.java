package cleo;

public enum CommandType {
    TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE, FIND, TASKS, UNKNOWN;

    /**
     * Converts a string to its corresponding CommandType.
     * Returns UNKNOWN if the string does not match any command type.
     *
     * @param commandString The command string to convert.
     * @return The corresponding CommandType.
     */
    public static CommandType fromString(String commandString) {
        try {
            return CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
