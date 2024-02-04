package Jerry.command;

import Jerry.Task;
import Jerry.TaskList;
import Jerry.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(Ui ui, TaskList tasks, String keyword) {
        super(ui, tasks);
        this.keyword = keyword;
    }

    @Override
    public void execute() {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        ui.showTaskSearchResults(foundTasks);
    }
}
