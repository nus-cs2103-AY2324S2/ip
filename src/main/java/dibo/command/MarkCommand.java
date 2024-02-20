package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;

/**
 * The MarkCommand class represents a command to mark a task in TaskList as done.
 */
public class MarkCommand extends Command {
    private final int taskId;

    /**
     * Constructs a new MarkCommand object with the specified parameters.
     *
     * @param taskId The index of the task to be mark as done in the TaskList.
     */
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.storeMarkedMessage(taskList.markTask(taskId));
        storage.saveData(taskList);
    }
}
