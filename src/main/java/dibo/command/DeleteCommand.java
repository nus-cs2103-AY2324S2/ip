package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;

/**
 * Class to handle a command which deletes a task.
 */
public class DeleteCommand extends Command {
    private final int taskId;

    /**
     * Constructor for the DeleteCommand class.
     *
     * @param taskId The index of the task to be deleted in the TaskList.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Run the delete task command.
     *
     * @param taskList The TaskList object which contains all the tasks.
     * @param ui The Ui object which is responsible for printing the marked message.
     * @param storage The Storage object which is responsible to save the changes into a text file.
     * @throws DiboException when an error occurs when trying to save the changes into a text file.
     */
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.showDeleted(taskList.deleteTask(taskId), taskList.getSize());
        storage.saveData(taskList);
    }

}
