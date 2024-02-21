package command;

import cleo.TaskList;
import cleo.UI;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, UI ui) {
        return ui.showGoodbyeMessage();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
