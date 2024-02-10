package duke;

/**
 * Class that represents a command that can be executed.
 */
public abstract class Command {
    /**
     * Enum that represents the type of the command.
     */
    enum CommandType {
        LIST,
        DELETE,
        ADD,
        MARK,
        FIND
    }

    private String text;
    private CommandType type;

    /**
     * Constructor for the command.
     *
     * @param text The text of the command.
     * @param type The type of the command.
     */
    public Command(String text, CommandType type) {
        this.text = text;
        this.type = type;
    }

    /**
     * Gets the type of the command.
     *
     * @return The type of the command.
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Gets the type of the command.
     *
     * @return The type of the command.
     */
    public String getText() {
        return text;
    }

    /**
     * Executes the command.
     *
     * @param state The state of the app.
     * @param ui    The user interface of the app.
     * @return
     */
    public abstract String execute(State state, Ui ui);
}
