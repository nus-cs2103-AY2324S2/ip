package youdon;

/**
 * Enum representing valid commands recognized by the Youdon chatbot.
 */
public enum ValidCommands {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    BYE("bye"),
    DELETE("delete"),
    FIND("find"),
    SNOOZE("snooze");


    private final String command;

    /**
     * Constructs a validCommands enum with the specified command string.
     *
     * @param command The command string associated with the enum.
     */
    ValidCommands(String command) {
        this.command = command;
    }


    /**
     * Retrieves the command string associated with this enum.
     *
     * @return The command string.
     */
    public String getCommand() {
        return this.command;
    }
}
