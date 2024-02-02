package command;

import ping.TaskList;
import ping.UI;

public class HiCommand extends Command {

    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        ui.hiMessage();
        return null;
    }
}
