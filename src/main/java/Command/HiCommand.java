package Command;


import Ping.TaskList;
import Ping.UI;


/**
 * This class is used to respond to the user
 */
public class HiCommand extends Command {

    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        ui.hiMessage();
        return null;
    }
}
