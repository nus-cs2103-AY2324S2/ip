package commands;

import exceptions.ConvoBotException;
import utils.TaskList;
import utils.UI;

public class Mark implements Command {
    private final int i;

    public Mark(int i) {
        this.i = i;
    }

    public void execute(TaskList taskList, UI ui) throws ConvoBotException {
        taskList.mark(i, true);
        ui.showMarked(taskList.getTaskString(i));
    }

    public boolean isExit() {
        return false;
    }
}
