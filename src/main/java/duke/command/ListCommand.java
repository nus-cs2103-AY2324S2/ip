package duke.command;

import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTask();
    }
}
