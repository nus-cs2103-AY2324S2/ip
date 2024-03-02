package ping.command;

import ping.TaskList;
import ping.gui.UI;



/**
 * This class is used to respond to the user
 */
public class BlahCommand extends Command {

    @Override
    public String perform(TaskList tasks, UI ui) {
        return ui.respondBlah();
    }
}
