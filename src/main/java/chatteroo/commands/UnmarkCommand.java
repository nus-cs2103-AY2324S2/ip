package chatteroo.commands;

import chatteroo.tasks.TaskList;
import chatteroo.ui.Ui;
import chatteroo.storage.Storage;

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
