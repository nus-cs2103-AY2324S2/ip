package raphael.command;

import raphael.exception.RaphaelException;
import raphael.storage.Storage;
import raphael.task.TaskList;
import raphael.ui.Ui;

/**
 * Prints the list of command.
 */
public class HelpCommand extends Command {
    /**
     * Executes the current command. Upon execution, output the list of available command.
     *
     * @param tasks the task list.
     * @param ui the user interface object.
     * @param storage the file I/O object.
     * @throws RaphaelException the exception exclusive for Raphael.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RaphaelException {
        ui.showHelpOutput();
    }
}
