package ben.commands;

import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Creates an ExitCommand.
     */
    public ExitCommand() {
    }

    /**
     * Executes the ExitCommand by displaying the exit message and line separator.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface to display messages.
     * @param storage The storage (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExitMessage() + ui.showLine();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return {@code true} since this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
