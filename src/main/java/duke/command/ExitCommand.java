package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.store(taskList);
        ui.showExitMessage();
    }
    public  boolean isExit() {
        return true;
    }
}
