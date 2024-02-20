package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;

/**
 * The UnmarkCommand class represents a command to mark a task in TaskList as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskId;

    /**
     * Constructs a new UnmarkCommand object with the specified parameters.
     *
     * @param taskId The index of the task to be mark as not done in the TaskList.
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.storeUnmarkedMessage(taskList.unmarkTask(taskId));
        storage.saveData(taskList);
    }

}
