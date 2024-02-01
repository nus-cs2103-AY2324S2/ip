package commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

public class AddDeadlineCommand extends AddCommand{
    public AddDeadlineCommand(Task task) {
        super(task);
    }
}
