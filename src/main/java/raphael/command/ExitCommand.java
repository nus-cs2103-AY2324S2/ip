package raphael.command;
import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;

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
        return ;
    }

    /**
     * Returns a boolean value indicating if the current command terminates the bot.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        System.out.println("Bye! It is an honor to serve you!");
        return true;
    }
}
