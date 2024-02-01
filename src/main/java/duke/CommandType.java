package duke;

public enum CommandType {
    TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE, LIST, INVALID;

    public static CommandType fromString(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }
}