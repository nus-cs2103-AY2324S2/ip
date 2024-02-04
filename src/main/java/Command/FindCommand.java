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
    public String execute(TaskList tasks, UI ui) {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        return ui.showTaskList(foundTasks);
    }
}
