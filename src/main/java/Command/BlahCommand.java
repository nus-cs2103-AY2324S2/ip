package Command;


import Ping.TaskList;
import Ping.UI;


/**
 * This class is used to respond to the user
 */
public class BlahCommand extends Command {

    @Override
    public String perform(TaskList tasks, UI ui) {
        return ui.respondBlah();
    }
}
