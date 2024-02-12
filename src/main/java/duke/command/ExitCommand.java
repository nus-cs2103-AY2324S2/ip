package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    /**
     * Signal the ChatBot to exit
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        storage.saveTaskListToFile(taskList);
        return ui.showFarewell();
    }
}
