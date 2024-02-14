package chaterpillar.commands;

import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

/**
 * <code>Command</code> to display message when the input is unrecognised.
 */
public class UnrecognisedCommand extends Command {

    /**
     * Prints out a reply and a help message when the input is unrecognised.
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String unrecognised =
                "Oops, I have no idea what that means. "
                + "Use 'help' for a list of commands I recognise.";
        ui.echo(unrecognised);
        return unrecognised;
    }
}
