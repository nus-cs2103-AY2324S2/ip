package commands;

import exceptions.ConvoBotException;
import utils.TaskList;
import utils.UI;

public class Unmark implements Command {
    private final int i;

    public Unmark(int i) {
        this.i = i;
    }

    public void execute(TaskList taskList, UI ui) throws ConvoBotException {
        taskList.mark(i, false);
        ui.showUnmarked(taskList.getTaskString(i));
    }

    public boolean isExit() {
        return false;
    }
}
