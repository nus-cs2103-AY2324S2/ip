package thames.command;

import thames.TaskList;
import thames.Ui;
import thames.Storage;

public class ExitCommand extends Command {
    /**
     * Notifies user with exit message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.bye();
    }
}
