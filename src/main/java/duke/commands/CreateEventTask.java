package duke.commands;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskException;
import duke.mainUtils.Parser;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

/**
 * The CreateEventTask class represents a command to create an event task.
 * <p>
 * This command parses the user input to create an event task and adds it to the task list.
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
 */
public class CreateEventTask extends Command {

    /**
     * Executes the command to create an event task.
     *
     * @param taskList the task list to add the new task to.
     * @param ui the user interface for interacting with the user.
     * @param storage the storage for saving the task.
     * @throws InvalidTaskException if the task is invalid.
     * @throws InvalidDateException if the date format is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException {
        taskList.addTask(Parser.parseEventTask(ui.getCommand()));
    }
}
