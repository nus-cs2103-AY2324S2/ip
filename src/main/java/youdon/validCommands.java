package youdon;

/**
 * Enum representing valid commands recognized by the Youdon chatbot.
 */
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

    /**
     * Constructs a validCommands enum with the specified command string.
     *
     * @param command The command string associated with the enum.
     */
    validCommands(String command) {
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
