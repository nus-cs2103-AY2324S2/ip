package duke.commands;

import duke.exceptions.StorageException;
import duke.fileUtils.FilePaths;
import duke.fileUtils.FileUtil;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;


/**
 * The Help class represents a command to display help information.
 * <p>
 * This command displays help information to assist users in understanding how to use the program.
 * </p>
 * <p>
 * It extends the Command class and implements the execute method to perform its action.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.commands.Command
 * @see duke.fileUtils.FilePaths
 * @see duke.fileUtils.FileUtil
 * @see duke.mainUtils.Storage
 * @see duke.mainUtils.Ui
 * @see duke.tasks.TaskList
 * @see duke.exceptions.StorageException
 */
public class Help extends Command {

    /**
     * Executes the command to display help information.
     *
     * @param taskList the task list.
     * @param ui the user interface.
     * @param storage the storage.
     * @throws StorageException if there is an error accessing the storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageException {
        FileUtil.displayFile(FilePaths.HELP_PATH);
    }
}
