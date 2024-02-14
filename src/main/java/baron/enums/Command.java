package baron.enums;

/**
 * Handles different types of user commands and input.
 */
public enum Command {
    LIST("list"),
    LIST_CLIENTS("clients"),
    FIND("find"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DELETE_CLIENT("discharge"),
    BYE("bye");


    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
