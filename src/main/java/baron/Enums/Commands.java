package baron.Enums;

public enum Commands {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    BYE("bye");


    private final String command;

    Commands (String command) {
        this.command = command;
    }

    public String getCommand () {
        return command;
    }
}
