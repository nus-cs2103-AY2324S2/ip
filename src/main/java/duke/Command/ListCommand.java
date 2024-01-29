package duke.Command;

import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;

//package duke.command;
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

