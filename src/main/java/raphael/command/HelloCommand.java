package raphael.command;

import raphael.Raphael;
import raphael.exception.RaphaelException;
import raphael.storage.Storage;
import raphael.task.TaskList;
import raphael.ui.Ui;

public class HelloCommand extends Command {

    /**
     * Executes the current command. This method is made abstract so that the command subclasses must override it
     * and provide their own implementations.
     *
     * @param tasks   the task list
     * @param ui      the user interface object
     * @param storage the file I/O object
     * @throws RaphaelException exception exclusive to Raphael
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RaphaelException {
        ui.saveOutput(Raphael.LOGO);
    }
}
