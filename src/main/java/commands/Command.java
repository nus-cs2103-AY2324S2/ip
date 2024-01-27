package commands;

import exceptions.ConvoBotException;
import utils.TaskList;
import utils.UI;

public interface Command {
    public void execute(TaskList taskList, UI ui) throws ConvoBotException;
    public boolean isExit();
}
