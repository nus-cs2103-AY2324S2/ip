package commands;

import utils.TaskList;
import utils.Ui;

public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.listFoundTasks(taskList.findTasks(query));
    }
}
