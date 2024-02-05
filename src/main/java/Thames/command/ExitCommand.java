package Thames.command;

import Thames.TaskList;
import Thames.Ui;
import Thames.Storage;

public class ExitCommand extends Command {
    /**
     * Notifies user with exit message.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }
}
