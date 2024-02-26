package duke.commands;

import duke.exceptions.*;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The DeleteTask class represents a command to delete a task from the task list.
 * <p>
 * This command parses the user input to determine the index of the task to delete and removes it from the task list.
 * </p>
 * <p>
 * It extends the Command class and implements the execute method to perform its action.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.commands.Command
 * @see duke.mainUtils.Storage
 * @see duke.mainUtils.Ui
 * @see duke.tasks.TaskList
 * @see duke.exceptions.InvalidIndexException
 * @see duke.exceptions.StorageException
 */
public class DeleteTask extends Command {

    /**
     * Executes the command to delete a task.
     *
     * @param taskList the task list from which the task will be deleted.
     * @param ui the user interface for interacting with the user.
     * @param storage the storage for saving the task list after deletion.
     * @throws InvalidIndexException if the index of the task to delete is invalid.
     * @throws StorageException if there is an error accessing the storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException, StorageException {
        String[] userInput = ui.getCommand();
        try {
            int index = Integer.parseInt(userInput[userInput.length - 1]) - 1;
            taskList.deleteTask(index);
            return doneExecute(taskList, ui, storage);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public String doneExecute(TaskList taskList, Ui ui, Storage storage){
        return "That task is goneee for goood. There's no pending tasks if they don't exist amirite.";
    }
}
