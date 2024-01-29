package duke.command;

import duke.exception.ChatBotParameterException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    /**
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * @param storage
     * @param ui
     * @param taskList
     */


    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        storage.saveTaskListToFile(taskList);
        ui.showFarewell();
    }
}
