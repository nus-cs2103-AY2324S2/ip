package commands;

import utils.TaskList;
import utils.UI;

public class Bye implements Command {
    public void execute(TaskList taskList, UI ui) {
        return;
    }

    public boolean isExit() {
        return true;
    }
}
