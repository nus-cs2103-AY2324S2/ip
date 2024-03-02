package ping.command;

import ping.TaskList;
import ping.gui.UI;

/**
 * This class is used to exit the program
 */
public class ExitCommand extends Command {
    @Override
    public String perform(TaskList tasks, UI ui) {
        return ui.goodBye();
    }

    @Override
    public boolean isRunning() {
        return false;
    }




}
