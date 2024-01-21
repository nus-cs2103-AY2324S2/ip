package messages;

enum CommandTypes {  // default access modifier
    EXIT("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    ADD("add");

    private String commandType;

    CommandTypes(String commandType) {  // default access modifier
        this.commandType = commandType;
    }

    static CommandTypes getCommandType(String input) {  // default access modifier
        for (CommandTypes commandType : CommandTypes.values()) {
            if (commandType.commandType.equals(Parser.getCommand(input))) {
                return commandType;
            }
        }
        return ADD;
    }
}
