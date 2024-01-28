package duke.command;

import duke.task.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(ui.getWriter());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
