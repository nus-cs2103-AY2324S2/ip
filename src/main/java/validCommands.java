public enum validCommands {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    BYE("bye"),
    DELETE("delete");

    private final String command;

    validCommands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }
}
