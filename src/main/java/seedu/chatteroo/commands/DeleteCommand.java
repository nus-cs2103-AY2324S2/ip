package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

/**
 * Deletes the specified task from the list of tasks.
 */
public class DeleteCommand extends Command {
    private int taskNum;
    public DeleteCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(taskNum);
    }
}
