package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

/**
 * Marks the specified task as done.
 */
public class MarkCommand extends Command {
    private int taskNum;

    /**
     * Constructor for the MarkCommand class.
     * @param taskNum The task number.
     */
    public MarkCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsDone(taskNum);
    }
}
