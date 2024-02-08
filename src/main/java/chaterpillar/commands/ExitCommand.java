package chaterpillar.commands;

import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;
public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo("Bye. Hope to see you again soon!");
    }
    public boolean hasExited() {
        return true;
    }
}
