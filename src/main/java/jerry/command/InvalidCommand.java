package jerry.command;

import jerry.Ui;

/**
 * Represents a command that is used when an invalid or unrecognized command is entered by the user.
 * <p>
 * This command handles any input that does not correspond to a valid command in the chatbot application,
 * providing feedback to the user about the incorrect input and suggesting possible corrections.
 */
public class InvalidCommand extends Command{
    /**
     * Constructs an {@code InvalidCommand} with the specified UI.
     * The task list is not required as no task manipulation occurs for invalid commands.
     *
     * @param ui The UI component for interacting with the user.
     */
    public InvalidCommand(Ui ui) {
        super(ui, null);
    }

    /**
     * Executes the invalid command process.
     * <p>
     * A message is displayed to the user indicating that the entered command is unrecognized or improperly formatted.
     */
    @Override
    public String execute() {
        return ui.showMessage("Invalid Command");
    }
}
