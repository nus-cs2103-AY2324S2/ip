package command;

import ping.TaskList;
import ping.UI;
public class ListCommand extends Command {
    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        ui.showList(tasks.allTasks());
        return tasks;
    }
}
