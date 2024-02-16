package duke.command;

import duke.Storage;
import duke.TaskList;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        super.markAsExit();
        return ui.showExitMessage();
    }
}
