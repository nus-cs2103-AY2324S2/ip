package commands;

import exceptions.FileError;
import tasklist.TaskList;
import ui.Ui;
import storage.Storage;

/**
 *  Command that signifies the closing of Blawg application
 *  Writes all data from the user's tasklist to a storage txt file
 *  and displays a goodbye message.
 */
public class ByeCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FileError {
        storage.write(taskList.getTaskList());
        ui.showBye();
    }



    @Override
    public Boolean isExit() {
        return true;
    }
}
