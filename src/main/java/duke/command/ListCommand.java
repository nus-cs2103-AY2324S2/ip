package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.displayAllTasks(taskList);
    }
}
