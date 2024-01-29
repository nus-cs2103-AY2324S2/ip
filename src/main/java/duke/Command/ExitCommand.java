package duke.Command;

import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;


//package duke.command;
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
