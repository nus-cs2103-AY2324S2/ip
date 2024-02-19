package chaterpillar.commands;

import chaterpillar.storage.Storage;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to display message when the input is unrecognised.
 */
public class UnrecognisedCommand extends Command {
    private static final String UNRECOGNISED_MESSAGE = "Oops, I have no idea what that means.\n"
                                                       + "Use 'help' for a list of commands I recognise.";

    /**
     * Prints out a reply and a help message when the input is unrecognised.
     *
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     * @return unrecognised input message from the ChatBot.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo(UNRECOGNISED_MESSAGE);
        return UNRECOGNISED_MESSAGE;
    }
}
