package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TextUi;

public class ByeCommand extends Command {
    /**
     * Shows exit message and exit the program
     * @param tasksList A TaskList class that represents task list
     * @param ui A TextUi class that represents the ui
     * @param storage A Storage class which represents the storage of file
     */

    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) {
        ui.showExitMessage();
    }
    /**
     * Returns boolean value indicating whether to exit the program or not
     * @return a boolean value
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
