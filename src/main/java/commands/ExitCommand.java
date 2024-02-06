package commands;

import tasks.TaskList;
import ui.Ui;
import storage.Storage;
public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo("Bye. Hope to see you again soon!");
    }
    public boolean hasExited() {
        return true;
    }
}
