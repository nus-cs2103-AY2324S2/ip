package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;
import tasklist.tasks.Task;

/** Represents a command to list all tasks in the tasklist. Implements the Command interface. */
public class ListCommand implements Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String result = ("Here are your current tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            int itemNumber = i + 1;
            Task listItem = taskList.getTask(i);
            result += itemNumber + ". " + listItem + "\n";
        }
        return result;
    }
}
