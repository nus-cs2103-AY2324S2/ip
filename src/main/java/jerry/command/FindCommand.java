package jerry.command;

import jerry.Task;
import jerry.TaskList;
import jerry.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

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
