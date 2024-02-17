package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

/**
 * Marks the specified task as not done.
 */
public class UnmarkCommand extends Command {
    private int taskNum;

    /**
     * Constructor for the UnmarkCommand class.
     * @param taskNum The task number.
     */
    public UnmarkCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsNotDone(taskNum);
        return ui.showMarkNotDoneTaskResponse() + tasks.getTask(taskNum).toString();
    }
}
