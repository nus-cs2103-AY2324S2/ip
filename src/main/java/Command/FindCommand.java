package Command;

import duke.TaskList;
import duke.UI;
import duke.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public TaskList execute(TaskList tasks, UI ui) {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        ui.showTaskList(foundTasks);
        return tasks;
    }
}
