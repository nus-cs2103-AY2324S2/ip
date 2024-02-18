package command;

import duke.TaskList;
import duke.UI;
import duke.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String[] keywords;

    public FindCommand(String... keywords) {
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, UI ui) {
        ArrayList<Task> foundTasks = tasks.findTasks(keywords);
        return ui.showTaskList(foundTasks);
    }
}
