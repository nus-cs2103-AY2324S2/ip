package Command;

import Ping.TaskList;
import Ping.UI;

/**
 * This class is used to exit the program
 */
public class ExitCommand extends Command {
    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        ui.goodBye();

        return tasks;
    }

    @Override
    public boolean isRunning() {
        return false;
    }




}
