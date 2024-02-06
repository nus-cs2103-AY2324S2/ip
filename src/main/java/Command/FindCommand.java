package Command;

import Ping.TaskList;
import Ping.UI;


/**
 * This class is used to find the tasks
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        ui.findTaskMessage(tasks.findTasks(keyword));
        return tasks;
    }
    
}
