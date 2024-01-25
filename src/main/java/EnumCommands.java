public enum EnumCommands {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    GETCOMMANDS("get commands"),
    DELETE("delete"),
    BYE("bye");

    private String command;

    //constructor
    private EnumCommands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
