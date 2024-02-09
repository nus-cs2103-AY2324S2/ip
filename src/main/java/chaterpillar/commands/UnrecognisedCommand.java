package chaterpillar.commands;

import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

public class UnrecognisedCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String unrecognised =
                "Oops, I have no idea what that means. "
                + "Use 'help' for a list of commands I recognise.";
        ui.echo(unrecognised);
    }
}
