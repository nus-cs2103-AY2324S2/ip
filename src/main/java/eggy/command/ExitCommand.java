package eggy.command;

import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.ui.Ui;

/**
 * Represents a command to exit the chat bot.
 */
public class ExitCommand extends Command {
    /**
     * Prints a goodbye message.
     *
     * @param tasks The task list of the chat bot.
     * @param ui The user interface of the chat bot.
     * @param storage The storage of the chat bot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return Whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
