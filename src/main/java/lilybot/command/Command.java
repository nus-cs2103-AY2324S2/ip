package lilybot.command;

import lilybot.gui.Ui;
import lilybot.task.TaskList;

/**
 * Command interface.
 */
public interface Command {

    public String exceute(Ui ui, String command, TaskList taskList);
}
