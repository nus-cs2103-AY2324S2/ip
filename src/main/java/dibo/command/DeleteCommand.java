package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;

/**
 * The DeleteCommand class represents a command to delete a task from TaskList.
 */
public class DeleteCommand extends Command {
    private final int taskId;

    /**
     * Constructs a new DeleteCommand object with the specified parameters.
     *
     * @param taskId The index of the task to be deleted in the TaskList.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.storeDeletedMessage(taskList.deleteTask(taskId), taskList.getSize());
        storage.saveData(taskList);
    }

}
