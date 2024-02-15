package duke.commands;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.StorageException;
import duke.mainUtils.Parser;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

/**
 * The CreateDeadlineTask class represents a command to create a deadline task.
 * <p>
 * This command parses the user input to create a deadline task and adds it to the task list.
 * </p>
 * <p>
 * It extends the Command class and implements the execute method to perform its action.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.commands.Command
 * @see duke.mainUtils.Parser
 * @see duke.mainUtils.Storage
 * @see duke.mainUtils.Ui
 * @see duke.tasks.TaskList
 * @see duke.exceptions.InvalidTaskException
 * @see duke.exceptions.InvalidDateException
 * @see duke.exceptions.StorageException
 */
public class CreateDeadlineTask extends Command {

    /**
     * Executes the command to create a deadline task.
     *
     * @param taskList the task list to add the new task to.
     * @param ui the user interface for interacting with the user.
     * @param storage the storage for saving the task.
     * @throws InvalidTaskException if the task is invalid.
     * @throws InvalidDateException if the date format is invalid.
     * @throws StorageException if there is an error accessing the storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException, StorageException {
        taskList.addTask(Parser.parseDeadlineTask(ui.getCommand()));
    }
}
