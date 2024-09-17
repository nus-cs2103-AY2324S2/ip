package chatteroo.commands;

import chatteroo.ChatterooException;
import chatteroo.tasks.TaskList;
import chatteroo.ui.Ui;
import chatteroo.storage.Storage;
import chatteroo.tasks.Task;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatterooException {
        try {
            Task deletedTask = tasks.getTask(taskNum);
            tasks.deleteTask(taskNum);
            int listCount = tasks.getTaskListSize();
            return ui.showDeleteTaskResponse(deletedTask, listCount);
        } catch (IndexOutOfBoundsException e) {
            return ui.showInvalidTaskNumberResponse();
        }
    }
}
