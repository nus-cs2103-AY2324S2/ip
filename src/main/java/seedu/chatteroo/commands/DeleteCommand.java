package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;
import seedu.chatteroo.tasks.Task;

/**
 * Deletes the specified task from the list of tasks.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Constructor for the DeleteCommand class.
     * @param taskNum The task number.
     */
    public DeleteCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.getTask(taskNum);
        tasks.deleteTask(taskNum);
        int listCount = tasks.getTaskListSize();
        return ui.showDeleteTaskResponse(deletedTask, listCount);
    }
}
