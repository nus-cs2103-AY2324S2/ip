package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.storage.Storage;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * Abstract class for specific <code>Command</code> objects,
 * such as ExitCommand or TaskCommand.
 * @author marclamp
 */
public abstract class Command {
    /**
     * Executes the command (determined in the specific <code>Command</code>
     * classes).
     *
     * @param task the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     * @throws ChaterpillarException custom <code>Exception</code> for this application.
     */
    public abstract String execute(TaskList task, Ui ui, Storage storage)
            throws ChaterpillarException;

    /**
     * Default method to check if the command has exited the application.
     *
     * @return <code>boolean</code> which is default false.
     */
    public boolean hasExited() {
        return false;
    }
}
