package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 *  Handles the displaying of the user's task list to the user in a readable manner
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.getTaskList().size(); i++) {
            output.append(i + 1).append(".");
            output.append(tasks.getTaskList().get(i));
        }
        return String.valueOf(output);
    }

    @Override
    public String showUsage() {
        return "Usage: List";
    }
}
