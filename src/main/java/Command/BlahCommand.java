package Command;

import Ping.Task;
import Ping.TaskList;
import Ping.UI;


public class BlahCommand extends Command {

    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        ui.respondBlah();
        return null;
    }
}
