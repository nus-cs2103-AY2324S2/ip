package duke.commands;

import duke.exceptions.*;
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
     * @param ui       the user interface.
     * @param storage  the storage.
     * @return the exit message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return doneExecute(taskList, ui, storage);
    }

    @Override
    public String doneExecute(TaskList taskList, Ui ui, Storage storage) {
        return "Gbye mate. See you soon! RAHHH";
    }
}
