package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(TaskList tasks, Ui ui, Storage storage, String keyword) {
        super(tasks, ui, storage);
        this.keyword = keyword;
    }

    @Override
    public void execute() {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}
