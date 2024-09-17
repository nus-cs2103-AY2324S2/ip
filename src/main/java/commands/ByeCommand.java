package commands;

import exceptions.FileError;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 *  Command that signifies the closing of Blawg application
 *  Writes all data from the user's tasklist to a storage txt file
 *  and displays a goodbye message.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FileError {
        storage.write(tasks.getTaskList());
        return ui.showBye();
    }
    @Override
    public Boolean isExit() {
        return true;
    }

    @Override
    public String showUsage() {
        return "Usage: Bye";
    }
}
