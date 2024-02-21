package duke;

/**
 * Class that represents a command that can be executed.
 */
public abstract class Command {

    private final String text;

    /**
     * Constructor for the command.
     *
     * @param text The text of the command.
     */
    public Command(String text) {
        this.text = text;
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
     * @return The text output of the command
     */
    public abstract String execute(State state, Ui ui);
}
