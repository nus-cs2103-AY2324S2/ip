package oak.feedback.enums;

/**
 * Enums for all the possible commands provided by user
 */
public enum CommandEnum {
    BYE("bye"),
    FIND("find"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    REMINDER("reminder");
    public final String commandValue;

    private CommandEnum(String commandValue) {
        this.commandValue = commandValue;
    }

    public String getCommandValue() {
        return this.commandValue;
    }

    // @@author SherisseTJW-reused
    // Reused from https://www.baeldung.com/java-search-enum-values, Section 3. Searching an Enum by Value
    // with minor modifications
    public static CommandEnum getCommandEnum(String value) {
        for (CommandEnum commandEnum : values()) {
            if (commandEnum.getCommandValue().equalsIgnoreCase(value)) {
                return commandEnum;
            }
        }

        return null;
    }
}
