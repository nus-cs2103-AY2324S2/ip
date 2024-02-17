package Jerry.command;

import Jerry.Task;
import Jerry.TaskList;
import Jerry.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(Ui ui, TaskList tasks, String keyword) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        return ui.showTaskSearchResults(foundTasks);
    }
}
