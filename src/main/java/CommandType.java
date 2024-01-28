public enum CommandType {
    LIST("list", new ListCommand()),
    MARK("mark", new MarkCommand()),
    UNMARK("unmark", new UnmarkCommand()),
    TODO("todo", new TodoCommand()),
    DEADLINE("deadline", new DeadlineCommand()),
    EVENT("event", new EventCommand()),
    DELETE("delete", new DeleteCommand()),
    BYE("bye", new ByeCommand()),
    EMPTY("", new EmptyCommand()),
    INVALID("", new InvalidCommand());

    private Command command;
    private String commandString;

    private CommandType(String commandString, Command command) {
        this.commandString = commandString;
        this.command = command;
    }

    public String getCommandString() {
        return this.commandString;
    }

    public Command getCommand() {
        return this.command;
    }

    public static CommandType fromString(String commandString) {
        for (CommandType command : CommandType.values()) {
            if (command.getCommandString().equals(commandString)) {
                return command;
            }
        }
        return INVALID;
    }
}
