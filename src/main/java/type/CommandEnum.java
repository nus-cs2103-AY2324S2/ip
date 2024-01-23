package type;

public enum CommandEnum {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark");

    public final String commandValue;

    private CommandEnum(String commandValue) {
        this.commandValue = commandValue;
    }

    public String getCommandValue() {
        return this.commandValue;
    }
}
