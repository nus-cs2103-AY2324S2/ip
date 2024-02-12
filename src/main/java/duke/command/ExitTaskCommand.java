package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitTaskCommand extends Command {
    /**
     * Executes the command by marking it as an exit command.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The user interface (not used in this command).
     * @param storage  The storage (not used in this command).
     * @return
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        super.exit();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True to signal that it's time to exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
