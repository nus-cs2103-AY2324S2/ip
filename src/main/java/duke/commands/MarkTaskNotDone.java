package duke.commands;

import duke.exceptions.*;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

/**
 * The MarkTaskNotDone class represents a command to mark a task as not done.
 * <p>
 * This command marks the specified task as not done in the task list.
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
public class MarkTaskNotDone extends Command {

    /**
     * Executes the command to mark a task as not done.
     *
     * @param taskList the task list.
     * @param ui the user interface.
     * @param storage the storage.
     * @throws InvalidIndexException if there is an error with the task index.
     * @throws StorageException if there is an error accessing the storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException, StorageException {
        String[] userInput = ui.getCommand();
        try {
            int index = Integer.parseInt(userInput[userInput.length - 1]) - 1;
            taskList.getTask(index).markNotDone();
            return doneExecute(taskList, ui, storage);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public String doneExecute(TaskList taskList, Ui ui, Storage storage){
        return "Damn it ain't done yet? GET TO WORKKK!";
    }
}
