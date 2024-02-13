package duke.commands;

import duke.exceptions.InvalidIndexException;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

public class ListTasks extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException {
        taskList.displayTasks();
    }
}
