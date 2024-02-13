package cappy.command;

/**
 * Enumeration representing different command types in the Cappy application.
 *
 * <p>The {@code CommandType} enum encapsulates various command types that can be performed in the
 * Cappy application. Each enum value associates a command string with its corresponding concrete
 * command implementation.
 */
public enum CommandType {
    LIST("list", new ListCommand()),
    MARK("mark", new MarkCommand()),
    UNMARK("unmark", new UnmarkCommand()),
    TODO("todo", new TodoCommand()),
    DEADLINE("deadline", new DeadlineCommand()),
    EVENT("event", new EventCommand()),
    DELETE("delete", new DeleteCommand()),
    FIND("find", new FindCommand()),
    BYE("bye", new ByeCommand()),
    HELP("help", new HelpCommand()),
    EMPTY("", new EmptyCommand()),
    INVALID("", new InvalidCommand());

    private Command command;
    private String commandString;

    private CommandType(String commandString, Command command) {
        this.commandString = commandString;
        this.command = command;
    }

    /**
     * Returns the string representation of this enum value.
     *
     * @return The string representation of this enum value.
     */
    public String getCommandString() {
        return this.commandString;
    }

    public Command getCommand() {
        return this.command;
    }

    /**
     * Converts a string to a CommandType. INVALID will be the CommandType returned if there are no
     * other matches.
     *
     * @param commandString The string to be converted.
     * @return The CommandType of the given string.
     */
    public static CommandType fromString(String commandString) {
        for (CommandType command : CommandType.values()) {
            if (command.getCommandString().equals(commandString)) {
                return command;
            }
        }
        return INVALID;
    }
}
