package baron.enums;

/**
 * Handles different types of user commands and input.
 */
public enum Commands {
    LIST("list"),
    FIND("find"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    BYE("bye");


    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
