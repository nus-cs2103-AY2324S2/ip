package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;

/**
 * Class to handle a command which mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskId;

    /**
     * Constructor for the UnmarkCommand class.
     *
     * @param taskId The index of the task to be mark as not done in the TaskList.
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Run the mark task as not done command.
     *
     * @param taskList The TaskList object which contains all the tasks.
     * @param ui The Ui object which is responsible for printing the marked message.
     * @param storage The Storage object which is responsible to save the changes into a text file.
     * @throws DiboException when an error occurs when trying to save the changes into a text file.
     */
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.showUnmarked(taskList.unmarkTask(taskId));
        storage.saveData(taskList);
    }

}
