package command;

import helpers.TaskList;
import helpers.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.displayGoodByeMessage();
        this.setIsExit();
    }
}
