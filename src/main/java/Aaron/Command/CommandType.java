package aaron.command;

import aaron.exception.NonsenseCommandException;

public enum CommandType {
    ADDTASK("add"),
    SHOW_LIST("show"),
    MARK("mark"),
    UNMARK("unmark"),
    SEARCH("find"),
    DELETE("delete"),
    SNOOZE("snooze");

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Method that returns the command type given a string
     * @param commandType String entered by user
     * @return CommandType corresponding to the userinput
     * @throws NonsenseCommandException if command type is not known/valid
     */
    public static CommandType getCommandType(String commandType) throws NonsenseCommandException {
        for (CommandType command : CommandType.values()) {
            if (commandType.equalsIgnoreCase(command.getCommandString())) {
                return command;
            }
        }
        throw new NonsenseCommandException("unknown command: " + commandType);
    }

    /**
     * Getter that returns commandString
     * @return CommandType
     */
    public String getCommandString() {
        return commandType;
    }

}
