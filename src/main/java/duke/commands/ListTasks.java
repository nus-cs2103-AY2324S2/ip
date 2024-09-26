package duke.commands;

import duke.exceptions.*;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

/**
 * The ListTasks class represents a command to list all tasks.
 * <p>
 * This command displays all tasks currently in the task list to the user.
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
 */
public class ListTasks extends Command {

    /**
     * Executes the command to list all tasks.
     *
     * @param taskList the task list.
     * @param ui the user interface.
     * @param storage the storage.
     * @throws InvalidIndexException if there is an error with the task index.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException {
        StringBuilder result =  new StringBuilder();
        result.append(taskList.displayTasks()).append("\n");
        result.append(doneExecute(taskList, ui, storage));
        return result.toString();
    }

    @Override
    public String doneExecute(TaskList taskList, Ui ui, Storage storage) {
        return "As someone once said, WHO'S GONNA ROW THE BOATS?!!";
    }
}
