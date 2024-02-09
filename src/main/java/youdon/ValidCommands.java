package youdon;

public enum ValidCommands {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    BYE("bye"),
    DELETE("delete"),
    FIND("find");


    private final String command;

    ValidCommands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }
}
