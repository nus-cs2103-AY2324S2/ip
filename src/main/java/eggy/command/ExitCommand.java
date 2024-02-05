package eggy.command;

import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.ui.Ui;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Prints a goodbye message.
     *
     * @param tasks The task list of the chatbot.
     * @param ui The user interface of the chatbot.
     * @param storage The storage of the chatbot.
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
