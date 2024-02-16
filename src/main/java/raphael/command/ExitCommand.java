package raphael.command;

import raphael.storage.Storage;
import raphael.task.TaskList;
import raphael.ui.Ui;

/**
 * Terminates the bot.
 */
public class ExitCommand extends Command {

    /**
     * Does nothing upon execution.
     *
     * @param tasks the task list
     * @param ui the user interface object
     * @param storage the file I/O object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.saveOutput("Bye! It is an honor to serve you!");
    }

    /**
     * Returns a boolean value indicating if the current command terminates the bot.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
