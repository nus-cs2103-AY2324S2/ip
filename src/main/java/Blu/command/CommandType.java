package blu.command;

/**
 * Represents the types of commands that can be executed in the application.
 */
public enum CommandType {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    FIND,
    BYE,
    INVALID;

    /**
     * Converts a string to its corresponding CommandType.
     * If the string does not match any CommandType, INVALID is returned.
     *
     * @param cmdString The string to be converted to a CommandType.
     * @return The CommandType corresponding to the given string, or INVALID if no match is found.
     */
    public static CommandType fromString(String cmdString) {
        // Solution below adapted by
        // https://stackoverflow.com/questions/4936819/java-check-if-enum-contains-a-given-string
        try {
            return CommandType.valueOf(cmdString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.INVALID;
        }
    }
}
