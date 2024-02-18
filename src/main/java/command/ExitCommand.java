package command;

import duke.TaskList;
import duke.UI;

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
