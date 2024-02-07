package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;

/**
 * This command handles invalid user commands.
 */
public class InvalidCommand extends Command {
    /**
     * Constructs an {@code InvalidCommand} with the specified TaskList, Ui, and Storage.
     * This command is invoked when an unrecognized command is input by the user.
     *
     * @param tasks   The TaskList associated with the command, not used in this context.
     * @param ui      The Ui instance for user interaction.
     * @param storage The Storage instance, not used in this context.
     */
    public InvalidCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }

    /**
     * Executes the action for an invalid command. It will display an error message indicating
     * that an invalid command was entered.
     */
    @Override
    public String execute() {
        return ui.showError("Invalid command entered. Please try again.");
    }
}
