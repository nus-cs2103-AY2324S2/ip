package lilybot.Command;

import lilybot.Task.TaskList;
import lilybot.Gui.Ui;

public interface Command {

    public String exceute(Ui ui, String command, TaskList taskList);
}
