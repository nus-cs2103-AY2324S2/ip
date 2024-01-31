package blu.command;

public enum CommandType {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    BYE,
    INVALID;

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