package oak.feedback.enums;

/**
 * Enums for all the possible commands provided by user
 */
public enum CommandEnum {
    /** The Bye Command */
    BYE("bye"),
    /** The Find Command */
    FIND("find"),
    /** The List Command */
    LIST("list"),
    /** The Mark Command */
    MARK("mark"),
    /** The Unmark Command */
    UNMARK("unmark"),
    /** The Delete Command */
    DELETE("delete"),
    /** The Todo Command */
    TODO("todo"),
    /** The Deadline Command */
    DEADLINE("deadline"),
    /** The Event Command */
    EVENT("event"),
    /** The Reminder Command */
    REMINDER("reminder");
    public final String commandValue;

    CommandEnum(String commandValue) {
        this.commandValue = commandValue;
    }

    public String getCommandValue() {
        return this.commandValue;
    }

    /**
     * Searches for the corresponding CommandEnum with the commandValue == the value passed in as argument,
     * and returns it
     *
     * @param value
     * @return the Command Enum, or null if no such enum with matching commandValue exists
     */
    public static CommandEnum getCommandEnum(String value) {
        // @@author SherisseTJW-reused
        // Reused from https://www.baeldung.com/java-search-enum-values, Section 3. Searching an Enum by Value
        // with minor modifications
        for (CommandEnum commandEnum : values()) {
            if (commandEnum.getCommandValue().equalsIgnoreCase(value)) {
                return commandEnum;
            }
        }

        return null;
    }
}
