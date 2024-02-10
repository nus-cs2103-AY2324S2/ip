package Aaron.Command;
import Aaron.Exception.NonsenseCommandException;

public enum CommandType {
    ADDTASK("add"),
    BYE("bye"),
    SHOW_LIST("show"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete");

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    public static CommandType getCommandType(String commandType) throws NonsenseCommandException {
        for (CommandType command : CommandType.values()) {
            if (commandType.equalsIgnoreCase(command.getCommandString())) {
                return command;
            }
        }
        throw new NonsenseCommandException("unknown command: " + commandType);
    }

    public String getCommandString() {
        return commandType;
    }

}
