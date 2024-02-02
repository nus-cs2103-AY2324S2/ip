package Command;

import Ping.TaskList;
import Ping.UI;

public class HiCommand extends Command {

    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        ui.HiMessage();
        return null;
    }
}
