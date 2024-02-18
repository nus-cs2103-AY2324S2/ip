package bmo.command;

import bmo.task.Task;
import bmo.ui.Ui;
import bmo.util.Storage;
import bmo.util.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        int idx_counter = 1;
        for (Task currTask : tasks) {
            if (currTask.toString().contains(keyword)) {
                output.append(idx_counter).append(". ").append(currTask.getStatusIcon()).append(" ").append(currTask).append("\n");
            }
            idx_counter++;
        }
        ui.showLine();
        System.out.println(output.toString());
        ui.showLine();
    }
}
