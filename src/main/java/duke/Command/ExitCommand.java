package duke.Command;

import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;

/**
 * Represents a command to exit the application.
 */
//package duke.command;
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand, showing a goodbye message.
     *
     * @param tasks   The list of tasks (not used).
     * @param ui      The user interface.
     * @param storage The storage handler (not used).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showGoodbyeMessage();
    }


    /**
     * Checks if the command is an exit command.
     *
     * @return Always returns true, as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
