package ping.command;

import ping.TaskList;
import ping.exceptions.PingException;
import ping.gui.UI;

/**
 * This class is used to create a command
 */
public abstract class Command {
    public abstract String perform(TaskList tasks, UI ui) throws PingException;

    public boolean isRunning() {
        return true;
    }

}
