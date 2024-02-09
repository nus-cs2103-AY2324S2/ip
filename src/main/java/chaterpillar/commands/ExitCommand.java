package chaterpillar.commands;

import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

/**
 * <code>Command</code> to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program.
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates that the <code>Command</code> has exited the application.
     * Overrides the method to return <code>true</code>
     * @return <code>true</code>
     */
    @Override
    public boolean hasExited() {
        return true;
    }
}
