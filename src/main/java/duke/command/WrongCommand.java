package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class WrongCommand extends Command {
    public WrongCommand(String message) {
        super();
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showWrongFormat();
        ui.showValidCommands();
    }
    public  boolean isExit() {
        return false;
    }
}
