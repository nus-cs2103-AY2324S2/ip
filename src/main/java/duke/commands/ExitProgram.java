package duke.commands;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskException;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

/**
 * The ExitProgram class represents a command to exit the program.
 * <p>
 * This command is used to terminate the program execution.
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
 * @see duke.exceptions.InvalidTaskException
 * @see duke.exceptions.InvalidDateException
 */
public class ExitProgram extends Command {

    /**
     * Executes the command to exit the program.
     *
     * @param taskList the task list.
     * @param ui the user interface.
     * @param storage the storage.
     * @throws InvalidTaskException if there is an error with tasks.
     * @throws InvalidDateException if there is an error with dates.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException {
        System.out.println("     Thanks for using, see you again! RAHHH");
    }
}
