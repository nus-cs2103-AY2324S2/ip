package alpa.commands;

public enum CommandType {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;

    public static CommandType fromString(String commandString) {
        try {
            return CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }
}

