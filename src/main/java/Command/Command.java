package Command;
import Ping.TaskList;
import Ping.UI;

public abstract class Command {
    public abstract TaskList perform(TaskList tasks, UI ui);

    public boolean isRunning() {
        return true;
    }

}
