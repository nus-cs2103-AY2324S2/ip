package duke.command;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a command to exit the chat.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by displaying a goodbye message using Ui.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True as ExitCommand is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
