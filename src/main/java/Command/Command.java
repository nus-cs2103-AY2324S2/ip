package command;

import ping.TaskList;
import ping.UI;

public abstract class Command {
    public abstract TaskList perform(TaskList tasks, UI ui);

    public boolean isRunning() {
        return true;
    }

}
