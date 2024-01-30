package Command;

import duke.TaskList;
import duke.UI;

public class ExitCommand extends Command {
    @Override
    public TaskList execute(TaskList tasks, UI ui) {
        ui.showGoodbyeMessage();
        return tasks;
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
