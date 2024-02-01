package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

/**
 * Marks the specified task as not done.
 */
public class UnmarkCommand extends Command {
    private int taskNum;
    public UnmarkCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsNotDone(taskNum);
    }
}
