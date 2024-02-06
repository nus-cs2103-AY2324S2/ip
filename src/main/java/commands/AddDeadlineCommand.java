package commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

/**
 *  Extends {@link AddCommand} and specifies the adding of deadline task
 */
public class AddDeadlineCommand extends AddCommand {
    public AddDeadlineCommand(Task task) {
        super(task);
    }
}
