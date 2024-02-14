package Command;


import Ping.TaskList;
import Ping.UI;

/**
 * This class is used to list the tasks
 */
public class ListCommand extends Command {
    @Override
    public String perform(TaskList tasks, UI ui) {
        return ui.showList(tasks.allTasks());
    }
}
