package Command;

import Ping.PingException;
import Ping.TaskList;
import Ping.UI;

/**
 * This class is used to create a command
 */
public abstract class Command {
    public abstract String perform(TaskList tasks, UI ui) throws PingException;

    public boolean isRunning() {
        return true;
    }

}
