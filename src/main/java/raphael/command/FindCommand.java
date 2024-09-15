package raphael.command;

import raphael.exception.RaphaelException;
import raphael.storage.Storage;
import raphael.task.TaskList;
import raphael.ui.Ui;

/**
 * The command class for the find command.
 */
public class FindCommand extends Command {

    private final String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the current command. Upon execution, all the tasks that contain the keyword will be printed.
     *
     * @param tasks the task list.
     * @param ui the ui object.
     * @param storage the file I/O object.
     * @throws RaphaelException exception exclusive to Raphael.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RaphaelException {
        ui.showFindOutput(tasks.find(this.keyword));
    }
}
