package saopig.command;

import saopig.Storage;
import saopig.Ui;
import saopig.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
