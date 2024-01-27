package commands;

import exceptions.ConvoBotException;
import utils.TaskList;
import utils.UI;

public class Delete implements Command {
    private final int i;

    public Delete(int i) {
        this.i = i;
    }

    public void execute(TaskList taskList, UI ui) throws ConvoBotException {
        taskList.delete(i);
        ui.showRemoved(taskList.getTaskString(i), taskList.size());
    }

    public boolean isExit() {
        return false;
    }
}
