package commands;

import utils.TaskList;
import utils.UI;

public class List implements Command {
    public void execute(TaskList taskList, UI ui) {
        ui.showTaskList(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
