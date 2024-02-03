package Command;

import Ping.TaskList;
import Ping.UI;

/**
 * This class is used to create a command
 */
public abstract class Command {
    public abstract TaskList perform(TaskList tasks, UI ui);

    public boolean isRunning() {
        return true;
    }

}
