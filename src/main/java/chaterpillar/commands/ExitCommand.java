package chaterpillar.commands;

import chaterpillar.storage.Storage;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to exit the program.
 *
 * @author marclamp
 */
public class ExitCommand extends Command {

    /**
     * Exits the program.
     *
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     * @return exit message from the ChatBot
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "Bye. Hope to see you again soon!";
        ui.echo(output);
        return output;

    }

    /**
     * Indicates that the <code>Command</code> has exited the application.
     * Overrides the method to return <code>true</code>
     *
     * @return <code>true</code>
     */
    @Override
    public boolean hasExited() {
        return true;
    }
}
