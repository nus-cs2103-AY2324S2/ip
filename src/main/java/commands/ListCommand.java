package commands;

import util.Ui;
import util.TaskList;
import util.Storage;

/**
 * The ListCommand class represents a command to list all tasks stored in the task list.
 * It extends the Command class and implements the execute method to perform the listing operation.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks stored in the task list by calling the listTasks method of TaskList.
     *
     * @param taskList The TaskList containing the current tasks.
     * @param ui       The Ui instance for user interaction and output.
     * @param storage  The Storage instance for saving tasks or loading data.
     */
    @Override
    public UserCommand execute(TaskList taskList, Ui ui, Storage storage) {
       return new UserCommand(taskList.listTasks());
    }
}
